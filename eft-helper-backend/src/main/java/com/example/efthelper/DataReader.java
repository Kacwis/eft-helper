package com.example.efthelper;

import com.example.efthelper.model.*;
import com.example.efthelper.model.Map;
import com.example.efthelper.model.projection.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DataReader {

    private static final List<Item> itemList = new ArrayList<>();
    private static final List<Ammunition> ammoList = new ArrayList<>();
    private static final List<Trader> tradersList = new ArrayList<>();

    private static final ObjectMapper mapper = new ObjectMapper();


    private final static String[] traderNames = {"prapor", "therapist", "skier", "jaeger", "mechanic", "peacekeeper", "ragman"};

    public static void main(String[] args) {
        itemReader();
        itemList.forEach(it -> {
            try {
                itemCreationPostRequest(it);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ammoReader();
        ammoList.forEach(
                a -> ammoCreationPostRequest(a)
        );
        tradersReader();
        tradersList.forEach(DataReader::tradersCreationPostRequest);
        questReader();
        mapReader();
        hideoutReader();
    }


    private static void itemReader(){
        try {
            var reader = new BufferedReader(new FileReader("C:\\Users\\Kacpe\\EFTHelper\\tarkovdata-master\\items.en.json"));
            var stream = reader.lines();
            var list = stream.toList();
            for(int i = 1; i < list.size() - 1 ; i=i+5){
                parseItemObject(list.get(i+1), list.get(i+2), list.get(i+3));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void tradersReader(){
        var parser = new JSONParser();
        try {
            var reader = new BufferedReader(new FileReader("C:\\Users\\Kacpe\\EFTHelper\\tarkovdata-master\\traders.json"));
            var wholeFile = (JSONObject) parser.parse(reader);
            for(var traderName : traderNames){
                var currentTrader = (JSONObject) wholeFile.get(traderName);
                var trader = createTrader(currentTrader, traderName);
                tradersList.add(trader);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static Trader createTrader(JSONObject currentTrader, String traderName){
        var trader = new Trader();
        var id = (Long) currentTrader.get("id");
        var fullName = (String) currentTrader.get("name");
        var wiki = (String) currentTrader.get("wiki");
        var currencies = (List<String>) currentTrader.get("currencies");
        var loyaltiesJson = (JSONArray) currentTrader.get("loyalty");
        var loyaltiesList = new ArrayList<Loyalty>();
        loyaltiesJson.forEach(l -> {
            var level = (Long) ((JSONObject) l).get("level");
            var requiredLevel = (Long) ((JSONObject) l).get("requiredLevel");
            var requiredReputation = (Number) ((JSONObject) l).get("requiredReputation");
            var requiredSales = (Long) ((JSONObject) l).get("requiredSales");
            loyaltiesList.add(new Loyalty(level.intValue(), requiredLevel.intValue(),  requiredReputation.doubleValue(), requiredSales.longValue()));
        });
        trader.setId(id.intValue());
        trader.setFullName(fullName);
        trader.setName(traderName);
        trader.setWiki(wiki);
        trader.setCurrencies(currencies);
        trader.setLoyalties(loyaltiesList);
        return trader;
    }

    private static void ammoReader(){
        try {
            var reader = new BufferedReader(new FileReader("C:\\Users\\Kacpe\\EFTHelper\\tarkovdata-master\\ammunition.json"));
            var list = reader.lines().toList();
            for (int i = 2; i < list.size(); i=i+23) {
                var map = new HashMap<String, String>();
                for (int j = i; j < i+20 ; j++) {
                    var currentLine = list.get(j).replace("\"", "").replace(",", "");
                    if(currentLine.contains("ballistics"))
                        continue;
                    var splitted = currentLine.split(": ");
                    var key = splitted[0].replace(" ", "");
                    var value = splitted[1].replace("\\", "");
                    System.out.println(key + " " + value);
                    map.put(key, value);
                }
                var currentAmmo = new Ammunition(
                        map.get("id"),
                        map.get("name"),
                        map.get("shortName"),
                        Double.parseDouble(map.get("weight")),
                        map.get("caliber").replace("Caliber", ""),
                        Integer.parseInt(map.get("stackMaxSize")),
                        Boolean.parseBoolean(map.get("tracer")),
                        map.get("tracerColor"),
                        map.get("ammoType"),
                        Integer.parseInt(map.get("projectileCount")),
                        Integer.parseInt(map.get("damage")),
                        Integer.parseInt(map.get("armorDamage")),
                        Double.parseDouble(map.get("fragmentationChance")),
                        Double.parseDouble(map.get("ricochetChance")),
                        Double.parseDouble(map.get("penetrationChance")),
                        Integer.parseInt(map.get("penetrationPower")),
                        Integer.parseInt(map.get("accuracy")),
                        Integer.parseInt(map.get("recoil")),
                        Integer.parseInt(map.get("initialSpeed"))
                );
                ammoList.add(currentAmmo);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void questReader(){
        try {
            var reader = new BufferedReader(new FileReader("C:\\Users\\Kacpe\\EFTHelper\\tarkovdata-master\\quests.json"));
            var quests = (JSONArray) new JSONParser().parse(reader);
            for(var quest : quests){
                var currentQuest = (JSONObject) quest;
                var id = ((Long) currentQuest.get("id")).intValue();
                var questRequirement = ((JSONObject) currentQuest.get("require"));
                var splittedRequire = questRequirement.toJSONString().split("\"quests\"");
                var s = splittedRequire[1];
                var splittedS = s.split(",\"loyalty\"");
                s = splittedS[0];
                s = s.replace(":", "").replace("]", "").replace("[", "").replace("}", "");
                var questRequiredIds = new ArrayList<Integer>();
                if(!s.isEmpty()) {
                    var questsIds = s.split(",");
                    for(var questId : questsIds){
                        questRequiredIds.add(Integer.parseInt(questId));
                    }
                }
                questRequiredIds.forEach(System.out::println);
                var questRequirementLevel = ((Long) questRequirement.get("level")).intValue();
                var unlockedItems = (List<String>) currentQuest.get("unlocks");
                var title = (String) currentQuest.get("title");
                var exp = ((Long) currentQuest.get("exp")).intValue();
                var gameId = (String) currentQuest.get("gameId");
                var turningId = (Long) currentQuest.get("turnin");
                var wiki = (String) currentQuest.get("wiki");
                var giverId = (Long) currentQuest.get("giver");
                var reputationList = (JSONArray) currentQuest.get("reputation");
                var reputationsIdsList = new HashSet<Integer>();
                for(var rep : reputationList){
                    var currentRep = (JSONObject) rep;
                    var repObj = new NewQuestReputationDTO();
                    repObj.setReputation((Double) currentRep.get("rep"));
                    repObj.setTraderId(((Long) currentRep.get("trader")).intValue());
                    reputationsIdsList.add(sendReputationPostRequest(repObj));
                }
                var questObjectivesIds = new HashSet<Integer>();
                var objectivesList = (JSONArray) currentQuest.get("objectives");
                var questObjectiveDTOList = createQuestObjectives(objectivesList);
                questObjectiveDTOList.forEach(dto -> sendQuestObjectivePostRequest(dto));
                questObjectiveDTOList.forEach(dto -> questObjectivesIds.add(dto.getId()));
                var newQuestDTO = createQuestDTO(id,
                        questRequirementLevel,
                        questRequiredIds,
                        turningId,
                        giverId,
                        title,
                        wiki,
                        exp,
                        gameId,
                        questObjectivesIds,
                        reputationsIdsList,
                        unlockedItems );
                sendQuestPostRequest(newQuestDTO);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void hideoutReader(){
        try {
            var reader = new BufferedReader(new FileReader("C:\\Users\\Kacpe\\EFTHelper\\tarkovdata-master\\hideout.json"));
            var parser = new JSONParser();
            var wholeJson = (JSONObject) parser.parse(reader);
            var stationsJsonArray = (JSONArray) wholeJson.get("stations");
            var modulesJsonArray = (JSONArray) wholeJson.get("modules");
            stationsReader(stationsJsonArray);
            modulesReader(modulesJsonArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void modulesReader(JSONArray modules) {
        for(var module : modules){
            var currentModule = (JSONObject) module;
            var hideoutModule = createHideoutModule(currentModule);
            sendHideoutModulePostRequest(hideoutModule);
        }
    }

    private static void sendHideoutModulePostRequest(HideoutModuleDTO hideoutModule) {
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/hideout/modules");
        post.addHeader("Accept", "application/json");
        post.addHeader("Content-type", "application/json");
        try {
            var json = mapper.writeValueAsString(hideoutModule);
            post.setEntity(new StringEntity(json));
            var response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            client.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HideoutModuleDTO createHideoutModule(JSONObject currentModule) {
        var module = (String) currentModule.get("module");
        var level = ((Long) currentModule.get("level")).intValue();
        var id = ((Long) currentModule.get("id")).intValue();
        var stationId = ((Long) currentModule.get("stationId")).intValue();
        var requirementArray = (JSONArray) currentModule.get("require");
        var requirementIds = new ArrayList<Integer>();
        for (var requirement : requirementArray){
            var currentRequirement = createModuleRequirement((JSONObject) requirement);
            requirementIds.add(currentRequirement.getId());
            sendModuleRequirementPostRequest(currentRequirement);
        }
        var hideoutModuleDTO = new HideoutModuleDTO();
        hideoutModuleDTO.setId(id);
        hideoutModuleDTO.setLevel(level);
        hideoutModuleDTO.setRequirementsIds(requirementIds);
        hideoutModuleDTO.setStationId(stationId);
        hideoutModuleDTO.setModule(module);
        return hideoutModuleDTO;
    }

    private static void sendModuleRequirementPostRequest(ModuleRequirementDTO currentRequirement) {
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/hideout/modules/requirements");
        post.addHeader("Accept", "application/json");
        post.addHeader("Content-type", "application/json");
        try {
            var json = mapper.writeValueAsString(currentRequirement);
            post.setEntity(new StringEntity(json));
            var response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            client.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ModuleRequirementDTO createModuleRequirement(JSONObject currentRequirement){
        var type = (String) currentRequirement.get("type");
        String name = null;
        Integer traderId = null;
        if(currentRequirement.get("name") instanceof String)
            name = (String) currentRequirement.get("name");
        else if(currentRequirement.get("name") instanceof Long)
            traderId = ((Long) currentRequirement.get("name")).intValue();
        var quantity = ((Long) currentRequirement.get("quantity")).intValue();
        var id = ((Long) currentRequirement.get("id")).intValue();
        var requirement = new ModuleRequirementDTO();
        requirement.setId(id);
        requirement.setName(name);
        requirement.setQuantity(quantity);
        requirement.setType(type);
        requirement.setTraderId(traderId);
        return requirement;
    }

    private static void stationsReader(JSONArray stations) {
        for (var station : stations){
            var currentStation = (JSONObject) station;
            var hideoutStation = createStation(currentStation);
            sendStationPostRequest(hideoutStation);
        }
    }

    private static void sendStationPostRequest(HideoutStationDTO station) {
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/hideout/stations");
        post.addHeader("Accept", "application/json");
        post.addHeader("Content-type", "application/json");
        try {
            var json = mapper.writeValueAsString(station);
            post.setEntity(new StringEntity(json));
            var response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            client.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static HideoutStationDTO createStation(JSONObject currentStation){
        var id = ((Long) currentStation.get("id")).intValue();
        var locales = (JSONObject) currentStation.get("locales");
        var name = (String) locales.get("en");
        var function = (String) currentStation.get("function");
        var imgSource = (String) currentStation.get("imgSource");
        var hideoutStation = new HideoutStationDTO();
        hideoutStation.setId(id);
        hideoutStation.setFunction(function);
        hideoutStation.setName(name);
        hideoutStation.setImgSource(imgSource);
        return hideoutStation;
    }

    private static void mapReader(){
        String[] mapNamesList = {"factory", "customs", "interchange", "woods", "shoreline", "lab", "reserve", "lighthouse"};
        try {
            var reader = new BufferedReader(new FileReader("C:\\Users\\Kacpe\\EFTHelper\\tarkovdata-master\\maps.json"));
            var parser = new JSONParser();
            var wholeFileJson = (JSONObject) parser.parse(reader);
            for(var mapName : mapNamesList){
                var currentMapJsonObject = (JSONObject) wholeFileJson.get(mapName);
                var map = createMap(currentMapJsonObject);
                sendMapPostRequest(map);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private static Map createMap(JSONObject currentMapJsonObject){
        var id = ((Long) currentMapJsonObject.get("id")).intValue();
        var locale = (JSONObject) currentMapJsonObject.get("locale");
        var name = (String) locale.get("en");
        var wiki = (String) currentMapJsonObject.get("wiki");
        var description = ((String) currentMapJsonObject.get("description"));
        if(description.length() > 254)
            description = description.substring(0, 254);
        var enemies = (List<String>) currentMapJsonObject.get("enemies");
        var raidDurationJsonObject = (JSONObject) currentMapJsonObject.get("raidDuration");
        var raidDurationDay = ((Long) raidDurationJsonObject.get("day")).intValue();
        var raidDurationNight = ((Long) raidDurationJsonObject.get("night")).intValue();
        var map = new Map();
        if(currentMapJsonObject.containsKey("svg")) {
            var svgJsonObject = (JSONObject) currentMapJsonObject.get("svg");
            var fileName = (String) svgJsonObject.get("file");
            map.setFileName(fileName);
        }
        map.setDescription(description);
        map.setId(id);
        map.setName(name);
        map.setWiki(wiki);
        map.setEnemies(enemies);
        map.setRaidDurationNight(raidDurationNight);
        map.setRaidDurationDay(raidDurationDay);
        System.out.println(id + " " + name);
        return map;
    }

    private static void sendMapPostRequest(Map map) {
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/maps");
        post.addHeader("Accept", "application/json");
        post.addHeader("Content-type", "application/json");
        try {
            var json = mapper.writeValueAsString(map);
            post.setEntity(new StringEntity(json));
            System.out.println(json);
            var response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static NewQuestDTO createQuestDTO(Integer id,
                                       int questRequirementLevel,
                                       List<Integer> questRequiredIds,
                                       Long turningId,
                                       Long giverId,
                                       String title,
                                       String wiki,
                                       int exp,
                                       String gameId,
                                       Set<Integer> questObjectivesIds,
                                       Set<Integer> reputationsIdsList,
                                       List<String> unlockedItems) {
        var questDTO = new NewQuestDTO();
        questDTO.setId(id);
        questDTO.setQuestRequirementLevel(questRequirementLevel);
        questDTO.setQuestRequiredIds(questRequiredIds);
        questDTO.setTurningTraderId(turningId.intValue());
        questDTO.setGiverTraderId(giverId.intValue());
        questDTO.setTitle(title);
        questDTO.setWiki(wiki);
        questDTO.setExp(exp);
        questDTO.setGameId(gameId);
        questDTO.setQuestObjectivesIds(questObjectivesIds);
        questDTO.setQuestReputationsIds(reputationsIdsList);
        questDTO.setUnlockedItemsIds(unlockedItems);
        return questDTO;
    }

    private static void sendQuestPostRequest(NewQuestDTO questDTO){
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/quests");
        post.addHeader("Accept", "application/json");
        post.addHeader("Content-type", "application/json");
        try {
            var json = mapper.writeValueAsString(questDTO);
            post.setEntity(new StringEntity(json));
            var response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendQuestObjectivePostRequest(QuestObjectiveDTO dto) {
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/quest_objectives");
        var data = new ArrayList<BasicNameValuePair>();
        data.add(new BasicNameValuePair("id", dto.getId().toString()));
        data.add(new BasicNameValuePair("type", dto.getType()));
        data.add(new BasicNameValuePair("target", dto.getTarget()));
        data.add(new BasicNameValuePair("number", dto.getNumber().toString()));
        if(dto.getLocation() != null)
            data.add(new BasicNameValuePair("location", dto.getLocation().toString()));
        if(dto.getHint() != null)
            data.add(new BasicNameValuePair("hint", dto.getHint()));
        if(dto.getToolId() != null)
            data.add(new BasicNameValuePair("toolId", dto.getToolId()));
        if(dto.getWith() != null){
            for(int i = 0; i < dto.getWith().size(); i++){
                try {
                    data.add(new BasicNameValuePair("with[]", dto.getWith().get(i)));
                }
                catch (ClassCastException e){
                    continue;
                }
            }
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(data));
            var response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            client.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<QuestObjectiveDTO> createQuestObjectives(JSONArray list){
        var objectivesList = new ArrayList<QuestObjectiveDTO>();
        for(var objective : list){
            var currentObjectiveDTO = new QuestObjectiveDTO();
            var currentObjective = (JSONObject) objective;
            currentObjectiveDTO.setId(((Long) currentObjective.get("id")).intValue());
            currentObjectiveDTO.setType((String) currentObjective.get("type"));
            currentObjectiveDTO.setTarget((String) currentObjective.get("target"));
            currentObjectiveDTO.setNumber(((Long) currentObjective.get("number")).intValue());
            if(currentObjective.containsKey("location"))
                currentObjectiveDTO.setLocation(((Long) currentObjective.get("location")).intValue());
            if(currentObjective.containsKey("hint"))
                currentObjectiveDTO.setHint((String) currentObjective.get("hint"));
            if(currentObjective.containsKey("tool"))
                currentObjectiveDTO.setToolId((String) currentObjective.get("tool"));
            if(currentObjective.containsKey("with"))
                currentObjectiveDTO.setWith((List<String>) currentObjective.get("with"));
            objectivesList.add(currentObjectiveDTO);
        }
        return objectivesList;
    }

    private static void parseItemObject(String id, String name, String shortname){
        var preparedId = prepareString(id, false);
        var preparedName= prepareString(name, false);
        var preparedShortname = prepareString(shortname, true);
        preparedName =  preparedName.replace("\\\"", "");
        itemList.add(new Item(preparedId, preparedName, preparedShortname));
    }

    private static Integer sendReputationPostRequest(NewQuestReputationDTO questReputationDTO){
        Integer toReturn = -1;
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/quest_reputation");
        var data = new ArrayList<BasicNameValuePair>();
        data.add(new BasicNameValuePair("traderId", questReputationDTO.getTraderId().toString()));
        data.add(new BasicNameValuePair("reputation", questReputationDTO.getReputation().toString()));
        try {
            post.setEntity(new UrlEncodedFormEntity(data));
            var response = client.execute(post);
            var stream = response.getEntity().getContent();
            var reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            var parser = new JSONParser();
            var responseObject = (JSONObject) parser.parse(getResponseContent(reader));
            toReturn = ((Long) responseObject.get("id")).intValue();
            client.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------- " + toReturn);
        return toReturn;
    }

    private static String getResponseContent(InputStreamReader streamReader) throws IOException {
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        var out = new StringBuilder();
        for(int numRead; (numRead = streamReader.read(buffer, 0, buffer.length)) > 0;){
            out.append(buffer, 0 , numRead);
        }
        return out.toString();
    }

    private static String prepareString(String value, boolean isLast){
        var splitted = value.split(":");
        String substring;
        if(isLast)
            substring = splitted[1].substring(2, splitted[1].length() - 1);
        else
            substring = splitted[1].substring(2, splitted[1].length() - 2);
        return substring;
    }

    private static void itemCreationPostRequest(Item item) throws IOException {
        var client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/api/items");
        var data = new ArrayList<BasicNameValuePair>();
        data.add(new BasicNameValuePair("id", item.getId()));
        data.add(new BasicNameValuePair("name", item.getName()));
        data.add(new BasicNameValuePair("shortname", item.getShortname()));
        post.setEntity(new UrlEncodedFormEntity(data));
        var response = client.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());
        client.close();
    }


    private static void ammoCreationPostRequest(Ammunition ammo){
        var client = HttpClients.createDefault();
        var post = new HttpPost("http://localhost:8080/api/ammo");
        var data = new ArrayList<BasicNameValuePair>();
        data.add(new BasicNameValuePair("id", ammo.getId()));
        data.add(new BasicNameValuePair("name", ammo.getName()));
        data.add(new BasicNameValuePair("shortName", ammo.getShortName()));
        data.add(new BasicNameValuePair("weight", ammo.getWeight().toString()));
        data.add(new BasicNameValuePair("caliber", ammo.getCaliber()));
        data.add(new BasicNameValuePair("stackMaxSize", ammo.getStackMaxSize().toString()));
        data.add(new BasicNameValuePair("tracer", ammo.getTracer().toString()));
        data.add(new BasicNameValuePair("tracerColor", ammo.getTracerColor()));
        data.add(new BasicNameValuePair("ammoType", ammo.getAmmoType()));
        data.add(new BasicNameValuePair("projectileCount", ammo.getProjectileCount().toString()));
        data.add(new BasicNameValuePair("damage", ammo.getDamage().toString()));
        data.add(new BasicNameValuePair("armorDamage", ammo.getArmorDamage().toString()));
        data.add(new BasicNameValuePair("fragmentationChance", ammo.getFragmentationChance().toString()));
        data.add(new BasicNameValuePair("ricochetChance", ammo.getRicochetChance().toString()));
        data.add(new BasicNameValuePair("penetrationChance", ammo.getPenetrationChance().toString()));
        data.add(new BasicNameValuePair("penetrationPower", ammo.getPenetrationPower().toString()));
        data.add(new BasicNameValuePair("accuracy", ammo.getAccuracy().toString()));
        data.add(new BasicNameValuePair("recoil", ammo.getRecoil().toString()));
        data.add(new BasicNameValuePair("initialSpeed", ammo.getInitialSpeed().toString()));
        try {
            post.setEntity(new UrlEncodedFormEntity(data));
            var response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            client.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tradersCreationPostRequest(Trader trader){
        var client = HttpClients.createDefault();
        var request = new HttpPost("http://localhost:8080/api/traders");
        var data = new ArrayList<BasicNameValuePair>();
        data.add(new BasicNameValuePair("id", trader.getId().toString()));
        data.add(new BasicNameValuePair("name", trader.getName()));
        data.add(new BasicNameValuePair("fullName", trader.getFullName()));
        data.add(new BasicNameValuePair("wiki", trader.getWiki()));
        for (int i = 0; i < trader.getCurrencies().size(); i++) {
            data.add(new BasicNameValuePair("currencies[]", trader.getCurrencies().get(i)));
        }
        for (int i = 0; i < trader.getLoyalties().size(); i++) {
            JSONObject currentLoyalty = new JSONObject();
            currentLoyalty.put("level", String.valueOf(trader.getLoyalties().get(i).getLevel()));
            currentLoyalty.put("requiredLevel", String.valueOf(trader.getLoyalties().get(i).getRequiredLevel()));
            currentLoyalty.put("requiredReputation", String.valueOf(trader.getLoyalties().get(i).getRequiredReputation()));
            currentLoyalty.put("requiredSales", String.valueOf(trader.getLoyalties().get(i).getRequiredSales()));
            data.add(new BasicNameValuePair("loyalty[]", currentLoyalty.toJSONString()));
        }
        try {
            request.setEntity(new UrlEncodedFormEntity(data));
            var response = client.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
            client.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
