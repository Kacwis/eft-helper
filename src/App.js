import { Routes, Route } from "react-router-dom";

import "./App.css";

import Layout from "./components/layout/Layout";

import HomePage from "./pages/HomePage";
import ItemsPage from "./pages/ItemsPage";
import SignUpPage from "./pages/SignUpPage";
import LogInPage from "./pages/LogInPage";
import AccountPanel from "./pages/AccountPanel";

function App() {
	return (
		<Layout>
			<Routes>
				<Route path="/home" element={<HomePage />} />
				<Route path="/items" element={<ItemsPage />} />
				<Route path="/sign-in" element={<SignUpPage />} />
				<Route path="/log-in" element={<LogInPage />} />
				<Route path="/account-panel" element={<AccountPanel />} />
			</Routes>
		</Layout>
	);
}

export default App;
