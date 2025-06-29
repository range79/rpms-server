const API = "/v1/auth";

function postJSON(url, body) {
  return fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  }).then(r => r.ok ? r.json() : Promise.reject(r));
}

const loginF = document.getElementById("loginForm");
if (loginF) {
  loginF.onsubmit = async e => {
    e.preventDefault();
    const { username, password } = Object.fromEntries(new FormData(loginF));
    const resp = await postJSON(`${API}/login`, { username, password });
    localStorage.setItem("rpms_token", resp.data);
    alert("Logged in!"); location.href = "dashboard.html";
  };
}

const regF = document.getElementById("registerForm");
if (regF) {
  regF.onsubmit = async e => {
    e.preventDefault();
    const { username, email, password } = Object.fromEntries(new FormData(regF));
    const resp = await postJSON(`${API}/register`, { username, email, password });
    localStorage.setItem("rpms_token", resp.data);
    alert("Account created!"); location.href = "login.html";
  };
}
