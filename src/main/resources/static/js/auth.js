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
    const { email, password } = Object.fromEntries(new FormData(loginF));
    const resp = await postJSON("/v1/auth/login", { email, password });
    localStorage.setItem("rpms_token", resp.data);
    alert("Logged in!"); location.href = "index.html";
  };
}

const regF = document.getElementById("registerForm");
if (regF) {
  regF.onsubmit = async e => {
    e.preventDefault();
    const { username, email, password } = Object.fromEntries(new FormData(regF));
    const resp = await postJSON("/v1/auth/register", { username, email, password });
    localStorage.setItem("rpms_token", resp.data);
    alert("Account created!"); location.href = "index.html";
  };
}
