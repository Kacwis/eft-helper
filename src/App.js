import { Routes, Route } from "react-router-dom";

import "./App.css";

import Layout from "./components/layout/Layout";

import HomePage from "./pages/HomePage";
import ItemsPage from "./pages/ItemsPage";
import SignUpPage from "./pages/SignUpPage";
import LogInPage from "./pages/LogInPage";
import AccountPanel from "./pages/AccountPanel";
import MyHideoutPage from "./pages/MyHideoutPage";

import StationDetails from "./components/my-hideout/stations/StationDetails";
import QuestDetails from "./components/my-hideout/quests/QuestDetails";

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
					<Route path="station/:stationId" element={<StationDetails />} />
					<Route path="quest/:questId" element={<QuestDetails />} />
				</Route>
			</Routes>
		</Layout>
	);
}

export default App;
