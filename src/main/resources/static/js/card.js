
// Değişken Tanımlama
const cardArea = document.getElementById("cardArea");
const cardTemplate = document.getElementById("cardTemplate");


// İlk Kartın Görünürlüğünü Kapatıyoruz
cardTemplate.style.display = "none";

// Kart Verileri
// Kartta Herhangi Bir Fotoğraf Değişeceği Durumda Sonda Ki .png , .jpg Uzantısını Değiştirmeyi Unutmayın!
const cardsData = [
  { header: "<JWT Authentication/>", text: "Stateless kimlik doğrulama sistemi.Access token üzerinden doğrulama yapılır. Refresh token + HttpOnly cookie desteği yakında geliyor." , image : "./images/image1.jpg"},
  { header: "<Friend Request System/>", text: "Kullanıcılar arasında ilişki kurulmasını sağlayan sistem.İstekler senderId, receiverId, status, createdAt alanlarıyla takip edilir." , image : "./images/image1.jpg" },
  { header: "<Layered Architecture/>", text: "Controller, Service, Repository, DTO gibi katmanlara ayrılmış sade ve bakımı kolay yapı.Microservice mimarisine geçişe uygundur." , image : "./images/image1.jpg" },
  { header: "<Full Swagger Docs/>", text: "Tüm API'ler Swagger üzerinden dökümante edilmiştir.Frontend ekipleri için kolay test ve örnek istek/yanıt desteği içerir." , image : "./images/image1.jpg" },
];


// Her Veri İçin Yeni Kart Açma
cardsData.forEach(data => {
  const newCard = cardTemplate.cloneNode(true);
  newCard.style.display = 'block';
  newCard.removeAttribute('id');

  newCard.querySelector('.cardHeader').textContent = data.header;
  newCard.querySelector('.cardText').textContent = data.text;
newCard.querySelector('.cardPhoto').style.backgroundImage = `url(${data.image})`;

  cardArea.appendChild(newCard);
});