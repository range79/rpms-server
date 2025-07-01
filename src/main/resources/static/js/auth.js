const API = "/v1/auth";

function postJSON(url, body) {
  return fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  }).then(r => r.ok ? r.json() : Promise.reject(r));
}

// handle login form submission
const loginF = document.getElementById("loginForm");
const errBox = document.getElementById("loginError");

if (loginF) {
  loginF.onsubmit = async e => {
    e.preventDefault();
    errBox.style.display = "none";  // hide error message before trying again

    const { username, password } = Object.fromEntries(new FormData(loginF));

    try {
      const resp = await postJSON(`${API}/login`, { username, password });
      localStorage.setItem("rpms_token", resp.data);
      location.href = "dashboard.html";
    } catch (err) {
      errBox.textContent =
        err.status === 401
          ? "Incorrect username or password."
          : "Login failed – please try again.";
      errBox.style.display = "block";
    }
  };
}

// handle register form submission
const regF = document.getElementById("registerForm");
const regErr = document.getElementById("registerError");

if (regF) {
  regF.onsubmit = async e => {
    e.preventDefault();
    regErr.style.display = "none";  // clear previous message

    const data = Object.fromEntries(new FormData(regF));

    if (data.password !== data.confirmPassword) {
      regErr.textContent = "Password does not match.";
      regErr.style.display = "block";
      return;
    }

    try {
      const resp = await postJSON(`${API}/register`, data);
      localStorage.setItem("rpms_token", resp.data);
      location.href = "dashboard.html";
    } catch (err) {
      regErr.textContent =
        err.status === 409
          ? "Username or email already exists."
          : "Registration failed – please try again.";
      regErr.style.display = "block";
    }
  };
}
