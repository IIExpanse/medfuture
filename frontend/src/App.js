import { useState } from "react";
import "./App.css"

function App() {

  const [login, setLogin] = useState("")
  const [validation, setValidation] = useState(true);


  const handleChange = (e) => {
    setLogin(e.target.value);
  };

  const handleSubmit = async (e) => {
    setValidation(login.includes("@") && login.length <= 25 && login.includes("."))
  

    // if (validation) {
    //   let response = (await fetch("http://localhost:3000")).json.toString;
    //   validation = response === "true";
    // }
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
          <p className={validation ? "not_error" : "error"} > Неверный логин или пароль</p>
          <input
            className={validation ? "inp1" : "inp1_err"}
            placeholder="Логин"
            value={login}
            onChange={handleChange}
          />

          <input placeholder="Пароль" />
          <button
            className="btn1"
            type="submit"
            onClick={handleSubmit}
          >
            Войти
          </button>

          <button className="btn2" >Забыл(а) пароль или логин</button>
        </div>
        <div className="div4">
          <p className="p3">Нужна помощь?</p>
          <a href="/">Нажми сюда</a>
        </div>
      </div>
    </div >
  );
}

export default App;
