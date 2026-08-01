# 🏋️ Fitness Kalori Hesaplama Paneli

Java Swing ile geliştirilmiş, egzersiz süresine göre yakılan kaloriyi hesaplayan basit ve şık bir masaüstü uygulaması.

## ✨ Özellikler

- 🎯 **Hazır Egzersiz Seçimi** — İp atlama, koşu, yürüyüş, bisiklet ve daha fazlası arasından seçim yapabilirsin
- ⏱️ **Süre Ayarlama** — Kaydırıcı (slider) ile 5-120 dakika arasında süre belirle
- 🔥 **Anlık Kalori Hesabı** — Seçilen egzersiz ve süreye göre yakılan kaloriyi hesaplar
- ⚡ **Hızlı Kayıt** — Listede olmayan özel egzersizleri isim, süre ve kalori bilgisiyle manuel ekleyebilirsin
- 📊 **Görsel Grafik** — Egzersiz geçmişini basit bir bar chart ile görselleştirir
- 📋 **Geçmiş Yönetimi** — Kayıtları tek tek veya toplu şekilde silebilirsin

## 🖼️ Ekranlar

| Ana Ekran | Egzersiz Paneli |
|---|---|
| Karşılama ekranı ve başlangıç butonu | Egzersiz seçimi, kalori hesaplama, geçmiş ve grafik |

## 🛠️ Kullanılan Teknolojiler

- **Java** (Swing / AWT)
- `CardLayout` ile ekranlar arası geçiş
- `JSlider`, `JComboBox`, `JList` gibi standart Swing bileşenleri
- Özel `ChartPanel` sınıfı ile `Graphics` API kullanılarak çizilen basit bar chart

## 🚀 Nasıl Çalıştırılır

### Gereksinimler
- JDK 17 veya üzeri (proje JDK 23 ile yapılandırılmış, uyumlu bir sürüm yeterli)

### Terminalden çalıştırma
```bash
cd src
javac Main.java AnaEkran.java Egzersiz.java
java Main
```

### IntelliJ IDEA ile çalıştırma
1. Projeyi IntelliJ IDEA'da aç
2. `src/Main.java` dosyasını sağ tıkla → **Run 'Main.main()'**

## 📁 Proje Yapısı

```
Panel/
├── src/
│   ├── Main.java        # Uygulama giriş noktası, ana pencere
│   ├── AnaEkran.java     # Karşılama / ana ekran arayüzü
│   └── Egzersiz.java     # Egzersiz seçimi, kalori hesaplama, grafik ve geçmiş
└── README.md
```

## 📌 Notlar

- Kalori hesaplama formülü basit bir çarpıma dayanır: `dakika × egzersiz katsayısı`. Gerçek kalori yakımı; kilo, boy, yaş ve metabolizma gibi faktörlere göre değişir, bu proje eğitim/demo amaçlıdır.
- Grafik verileri uygulama kapatıldığında sıfırlanır (kalıcı depolama yoktur).

## 📄 Lisans

Bu proje kişisel/eğitim amaçlı geliştirilmiştir.
