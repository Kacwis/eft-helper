import style from './AccountInfo.module.css';

const AccountInfo = (props) => {
    return <div className={style.main}>
        <div>
        <div className={style['info-row']}>
            <p className={style.label}>E-mail:</p>
            <p className={style.value}>{props.email}</p>
        </div>
        <div className={style['info-row']}>
            <p className={style.label}>Username:</p>
            <p className={style.value}>{props.username}</p>
        </div>
        </div>
        <div className={style.buttons}>
            <button className={style['password-change']}>Change password</button>
            <button className={style['remove-account']}>Remove account</button>
        </div>
    </div>
}

export default AccountInfo;