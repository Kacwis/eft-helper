import { useContext } from "react";

import style from './AccountPanel.module.css';

const AccountPanel = () => {
    const authCtx = useContext(AuthContext);

    return (<div>
        <h1>{`${authCtx.username}`}</h1>
        <div className={style.main}>
            <h2>Account info</h2>
            <div style={style['account-info']}></div>
        </div>
    </div>)
}

export default AccountPanel;