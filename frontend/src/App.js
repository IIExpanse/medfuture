import {useState} from "react";
import "./App.css"

function App() {

    const [login, setLogin] = useState("")
    const [psw, setPassword] = useState("");
    const [emailValidation, setEmailValidation] = useState(true);
    const [credentialsValidation, setCredentialsValidation] = useState(true);
    const [success, setSuccess] = useState(false);

    const handleLoginChange = (e) => {
        setLogin(e.target.value);
        setEmailValidation(login.includes("@") && login.length <= 25 && login.includes("."));
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = async (e) => {
        setEmailValidation(login.includes("@") && login.length <= 25 && login.includes("."));
        if (!emailValidation) {
            return;
        }
        e.preventDefault();

        let formData = new FormData();
        formData.append('username', login);
        formData.append('password', psw);

        const response = await fetch("http://auth-server:8080/login", {
            method: "POST",
            body: formData
        });

        if (response.status !== 200) {
            setSuccess(false);
            setCredentialsValidation(false);

        } else {
            setSuccess(true);
        }
    };

    return (
        <div className="App">
            <div className="div1">
                <p><b>Med</b>future</p>
            </div>
            <div className="div2">
                <p className="p1">Добро пожаловать!</p>
                <div className="div3">
                    <p className="p2">Введите данные для входа</p>
                    <p className={emailValidation ? "hidden" : "error"}> Неверный формат электронной почты</p>
                    <p className={credentialsValidation ? "hidden" : "error"}> Неверный логин или пароль</p>
                    <p className={!success ? "hidden" : "login_success"}> Успешный вход!</p>
                    <input
                        className={emailValidation ? "inp1" : "inp1_err"}
                        placeholder="Логин"
                        value={login}
                        onChange={handleLoginChange}
                    />

                    <input
                        placeholder="Пароль"
                        value={psw}
                        onChange={handlePasswordChange}
                    />
                    <button
                        className="btn1"
                        type="submit"
                        onClick={handleSubmit}
                    >
                        Войти
                    </button>

                    <button className="btn2">Забыл(а) пароль или логин</button>
                </div>
                <div className="div4">
                    <p className="p3">Нужна помощь?</p>
                    <a href="/">Нажми сюда</a>
                </div>
            </div>
        </div>
    );
}

export default App;
