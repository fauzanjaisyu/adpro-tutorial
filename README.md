# **ADV Shop**

# **Modul 1**
Pada modul 1 ini, setidaknya saya belajar mengenai coding standard seperti clean code, gitflow, secure coding, dan testing.

## **Reflection 1**

Pada latihan yang pertama, saya menerapkan prinsip clean code seperti pemberian nama yang jelas pada variabel dan fungsi yang saya gunakan.
Penamaan variabel dan fungsi yang baik memudahkan kita untuk membaca dan memahami tujuan dari kode yang ada. Dari penamaan yang baik juga
dapat meminimalisir penggunaan comment yang tidak perlu. Kemudian pada latihan ini saya juga belajar untuk menerapkan git flow. Saya mengelompokkan
fitur-fitur yang ada menjadi beberapa branch yang terpisah. Pada latihan ini saya juga belajar untuk membuat struktur SpringBoot yang rapi. Hal yang
dapat saya improve dari latihan ini mungkin saya dapat berlatih kembali untuk memahami git flow seperti proses merging dan branchingnya.

## **Reflection 2**

Pada latihan kedua, saya belajar untuk membuat unit test dan functional test. Sebelum memulai proses testing saya menambahkan beberapa dependencies
yang diperlukan. Setelah menulis unit test, saya dapat memastikan kode yang dibuat sudah berjalan sesuai dengan ekspektasi tanpa harus bolak-balik
menjalankan kode. Untuk pembuatan unit test tidak ada angka pasti untuk membuat seberapa banyak unti testnya, namun unit test yang dibuat
dapat menggunakan postive dan negative scenario untuk mengoptimalkannya. Menurut saya meski code coverage mencapai 100% belum tentu kode tsb 100% tidak
memiliki bugs, namun unit test tsb dirasa sudah cukup memadai.

# **Modul 2**

1. Code Quality Issue:
   Terdapat string yang redundant pada controller, kemudian disarankan oleh sonarCloud untuk mengubah string tersebut
   menjadi constant

2. Ya, CI/CD workflows yang saya buat sudah menerapkan konsep CI/CD. Karena setiap kali kode diintegrasikan, proses build dan pengujian kode dijalankan secara otomatis menggunakan GitHub Actions untuk memastikan bahwa perubahan tidak memecahkan aplikasi. Pada workflows tersebut akan melakukan tes otomatis seperti unit test dan tes lainnya yang relevan untuk memastikan
kualitas kode. Workflows tersebut juga akan melakukan deployment tiap kali saya melakukan git push kepada gitHub saya.

# **Modul 3**

1. Explain what principles you apply to your project!
   - Single Responsibilty Principle (SRP)
     Memisahkan kelas `CarController` dengan `ProductController` untuk memastikan bahwa tiap kelas hanya memiliki satu fungsi
   - Liskov Substitution Principle (LSP)
     Tidak melakukan inherit kelas `CarController` dengan `ProductController` karena keduanya memiliki perilaku yang berbeda
   - Interface Segregation Principle (ISP)
     Diterapkan pada `CarServiceImpl` yang mengimplementasikan `CarService` dan `ProductServiceImpl` yang mengimplementasikan `ProductService` 
   - Dependency Inversion Principle (DIP)
     Mengubah dependency `CarController` yang awalnya bergantung pada `CarServiceImpl` menjadi bergantung pada `CarService`

2. Explain the advantages of applying SOLID principles to your project with examples
   Keuntungan dari menerapkan prinsip ini adalah membuat kode yang ada menjadi lebih mudah dikelola dan diorganisir, membuat kode menjadi lebih fleksibel dan modular,
   mengurangi penulisan kode yang tidak perlu atau duplikasi, dan kode yang ditulis menjadi lebih mudah untuk dipahami oleh orang lain. Contohnya terdapat pada catatan diatas

3. Explain the disadvantages of not applying SOLID principles to your project with examples.
   Dengan tidak menerapkan prinsip ini, maka kode yang ditulis menjadi sulit dikembangkan, kesulitan dalam melakukan pengujian, kode menjadi tidak fleksibel, dan kode yang
   ditulis sulit dipahami orang lain, alias keterbalikan dari keuntungan yang sudah tertera pada nomor 2. Contohnya apabila saya tidak menerapkan prinsip SRP
   pada `CarController` maka kedepannya saya akan kesulitan untuk melakukan perubahan terhadap suatu fungsi, dengan dilakukan pemisahan berdasarkan fungsinya
   maka saya akan lebih mudah untuk melakukan perbaikan
