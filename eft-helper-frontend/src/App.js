import { Routes, Route } from "react-router-dom";

import "./App.css";

import Layout from "./components/layout/Layout";

import HomePage from "./pages/HomePage";
import ItemsPage from "./pages/ItemsPage";
import SignUpPage from "./pages/SignUpPage";
import LogInPage from "./pages/LogInPage";
import AccountPanel from "./pages/AccountPanel";
import MyHideoutPage from "./pages/MyHideoutPage";
import Quests from "./components/my-hideout/quests/Quests";
import Stations from "./components/my-hideout/stations/Stations";
import QuestDetails from "./components/my-hideout/quests/QuestsDetails";
import StationDetails from "./components/my-hideout/stations/StationDetails";

function App() {
	return (
		<Layout>
			<Routes>
				<Route path="/" element={<HomePage />} />
				<Route path="items" element={<ItemsPage />} />
				<Route path="sign-in" element={<SignUpPage />} />
				<Route path="log-in" element={<LogInPage />} />
				<Route path="account-panel" element={<AccountPanel />} />
				<Route path="my-hideout" element={<MyHideoutPage />}>
					<Route path="/my-hideout/quests" element={<Quests />}>
						<Route
							path="/my-hideout/quests/:questId"
							element={<QuestDetails />}
						/>
					</Route>

					<Route path="/my-hideout/stations" element={<Stations />}>
						<Route
							path="/my-hideout/stations/:stationId"
							element={<StationDetails />}
						/>
					</Route>
				</Route>
			</Routes>
		</Layout>
	);
}

export default App;
