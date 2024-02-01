# Final project automated-web-testing-jayjay
Projek automation testing untuk menguji web product store (https://www.demoblaze.com/) dan api untuk management user dan get list api tag pada tautan https://dummyapi.io/. Proyek ini dikembangkan menggunakan bahasa java (pembuatan script test) dan Groovy (build management)

# Build With
Proyek pengujian otomatis melibatkan enam buah library yang dibutuhkan.
- cucumber framework
- selenium framework
- webdriver manager
- rest assure
- testng
- assertion (tambahan)

# Getting Started

## Prerequisite
Sebelum menjalankan proyek ini, diperluan persyaratan environtmen yang harus disiapkan pada device eksekusi proyek
1. JDK diatas versi 8
2. Gradle Wrapper

## Instalation
Proses instalasi proyek ini pada local environment
1. Download source code git hub
2. Buka IDE pemrograman. Misalnya Intelj
3. Buat proyek baru dengan mode import source code existing
4. Lakukan pengaturan project bagian Java dan Gradle dengan
   - Arahkan path Java ke lokasi instakasi JDK
   - Gradle distribution diisi dengan Gradle Wraple

## File Configuration
Proses konfigurasi project menggunakan build automation pada File build.gradle. 
1. Semua dependency yang dibutuhkan harus ditambahkan di dalam dependencies. Penambahan kode dependency cukup mencari pada maven central. Berikut dependency yang dibutuhkan:
   a. cucumber, framework untuk mengenali syntax Gherkin sehingga penulisan test case dalam bahasa natural
   ```
    implementation 'io.cucumber:cucumber-java:7.15.0'
    testImplementation 'io.cucumber:cucumber-junit:7.15.0'
   ```
   b. selenium, framework untuk melakukan web locator dan melakukan aksi kepada web element saat melaksanakan eksekusi pengujian
   ```
    implementation 'org.seleniumhq.selenium:selenium-java:4.17.0'
   ```
   c. web driver manager, library untuk menjalankan driver web via selenium
   ```
    testImplementation 'io.github.bonigarcia:webdrivermanager:5.6.3'
   ```
   d. Rest Assurace, library untuk melakukan hit API
   ```
    testImplementation 'io.rest-assured:rest-assured:5.4.0'
   ```
   e. JSON, library untuk mengenali syntax json yang dikirim oleh API
   ```
    testImplementation 'org.json:json:20231013'
    testImplementation 'io.rest-assured:json-path:5.4.0'
    testImplementation 'io.rest-assured:json-schema-validator:5.4.0'
   ```
   f. TestNG dan Assertion, library untuk memutuskan test result (PASS / FAIL) dari sebuah kondisi pengujian
   ```
    testImplementation 'org.testng:testng:7.9.0'
    testImplementation 'org.assertj:assertj-core:3.25.2'
   ```
3. Pengaturan testing dengan kode:
```
  test {
    useTestNG()
      testLogging {
          events "PASSED", "SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR"
      }
      systemProperty "cucumber.filter.tags", System.getProperty("cucumber.filter.tags")
  }
```
3. Penambahan gradle task web testing dengan kode:
```
configurations {
    cucumberRuntime {
        extendsFrom testImplementation
    }
}

tasks.register('webTest') {
    dependsOn assemble, testClasses
    doLast {
        javaexec {
            main = "io.cucumber.core.cli.Main"
            classpath = configurations.cucumberRuntime + sourceSets.main.output + sourceSets.test.output
            args = [
                    '--plugin', 'pretty',
                    '--plugin', 'html:reports/cucumber-web-report.html',
                    '--glue', 'stepdefinitions',
                    '--tags', "@web",
                    'src/test/resources/features']
        }
    }
}
```    

# Struture Project Test
Tujuan project adalah proses pengujian automation web dab api, sehingga kode program tersimpan dalam package test. Adapun struktur package sbb:
```
{nama proyek}
  src
    java
    test
      java
        helper
          SetUpEndPoint.java
          Utility.java
        model
          apitesting
            Location
            UserProfile.Java
          webtesting
            ItemProduct.Java
            Order.Java
            PlaceOrder.Java
        runner
          TestRunner.java
        stepdefinitions
          apitesting
            {nama api}Step.java
          webtesting
            {nama halaman}Step.java
          Hooks.java
        testlogic
          apitesting
            {nama api}Test.java
          webtesting
            {nama halaman}Test.java

      resorces
        feature
          {nama halaman}.feature
    
```
- package helper berisi property supporting project, inisiasi driver web dan fungsi-fungsi umum untuk mendukung eksekusi pengujian.
- package model berisi class java yang menampung data pengujian. Data tersebut digunakan sebagai object yang akan diparsing dari steff definition menuju test logic
- package runner berisi class java untuk pengaturan eksekusi pengujian dan pengaturan test report.
- package stepdefinitions berisi kumpulan fungsi yang menghubungkan Gehrkin Steo dengan logika pemgogramannya
- package testlogic berisi test pemrograman dalam bentuk lower code yang akan menjalankan scenario step. 
- package feature berisi daftar file yang mengandung skenario pengujian dalam bahasa Gherkin.

# Struktur Pipeline Automation Testing Menggunakan Workflow
Proyek ini sudah menerapkan continous integration testing ketika terjadi perubahan kode program saat melakukan push ke GitHub [master], strtukr jobs yang dilakukan adalah
1. pengaturan environment : set up server, download repo ke server, set up java, instalasi browser, set up gradlew
2. eksekusi test case
3. mengarsipkan hasil laporan pengujian
4. melakukan upload report ke Github pages

Note.
1. Setiap kali menjalankan pipeline automation testing, akan terbentuk hasil test report yang dapat diakses pada tautan
https://asrimaspupah89.github.io/final-project-automation-testing-jayjay/
2. Untuk melihat history pipeline automation testing yang sudah dijalankan dapat dilihat pada menu action

# Workflow
Langkah pembuatan test script
1. Buat feature file yang mengandung langkah-langkah pengujia dalam bahasa natural
2. Buat kelas stepdefinition dalam bahasa java untuk melakukan panggilan method dari test pemrograman dalam pakacge pages
3. Buast test logic masing-masing operasi halaman. Wujud implementasinya biasanya melakukan web locator untuk mendapatkan web element dan pembuatan aksi yang dapat diberikan pada web elemen tersebut
4. Jika ada library lain yang dibutuhkan, maka dapat menambahkan pada file build.gradle bagian dependency
5. Jika ada test implementation yang bersifat umum untuk mendukung eksekusi pengujian, maka penambahan kode dapat dilakukan pada Class Utility atau membuat class baru pada package helper 

# How to Run Execution Testing
Proses menjalankan eksekusi testing teridiri dari dua cara, yaitu Terminal dan Class testRunner

## Terminal
Proses menjalankan eksekusi testing melalui terminal dengan menjalankan kode berikut:
```
./gradlew cucumberTest
```
Namun, sebelumnya harus dilakukan build terlebih dahulu agar library dependency sudah terkonfigurasi pada project. Kode build sbb:
```
./gradlew build
```
Tambahan, developer dapat melakukan clean library jika terjadi error pada saat build project dengan kode:
```
./gradlew clean
```
atau bisa juga clean dan build project dapat dilakukan secara bersama-sama dengan mengetikan kode:
```
./gradlew clean build
```
## Class TestRunner
Cukup dengan melakukan running test menggunakan icon run IDE pada Class TestRunner 

## Persiapan Data
Sebelum menjalankan test case, diperlukan persiapan data test: 
1. Pastikan id user valid saat menguji update user, delete user
2. Ubah parameter id user untuk update dan delete pada file apiuser.feature

# Software Under test
Pengujian dilakukan pada aplikasi web Product Store yang dapat diakses pada https://www.demoblaze.com/
* Fitur autentikasi meliputi Sign Up, Sign In, LogOut
* Fitur send message
* Fitur play video pada menu About Us
* Fungsi penambahan item product ke keranjang (Cart) pada halaman Landing Page
* Fungsi Purchase Order pada halaman Cart

Sementara, pada pengujian API menggunakan api management user dan get list Tag
* hit api get list user
* hit api get profile user by id
* hit api post new user
* hit api update user by id
* hit api delete user by id

# Test Case
Pembuatan test case meliputi test positif dan test negatif, yaitu
## Test Case Web Product Store
    1. Pemeriksaan berhasil Sign up
    2. Pemeriksaan gagal Sign up karena invalid mandatory checking, email already exist
    3. Pemeriksaan berhasil Sign In dan Log Out
    4. Pemeriksaan berhasil send message
    5. Pemeriksaan play video pada menu about us
    6. Pemeriksaan berhasil melakukan penambahan item ke keranjang
    7. Pemeriksaan berhasil melakukan purchase order

## Test Case API User dan Tag
    1. Pemeriksaan berhasil melakukan get semua daftar user
    2. Pemeriksaan berhasil melakukan get profile user by id
    3. Pemeriksaan gagal get profile user, karena user id tidak ditemukan
    4. Pemeriksaan berhasil ubah profile user by id
    5. Pemeriksaan berhasil hapus profile user by id

Note. Pendekatan pengujian menggunakan black box testing pada Fungsionalitas software level unit
Satuan unit adalah fitur atau fungsi software

# Author
<span style="font-size:0.7em;">
Asri Maspupah</br>
Dosen Informatika Jurusan Teknik Komputer dan Informatika</br>
Politeknik Negeri Bandung</br>
</span>

# Reference
Daftar resource yang dapat dipelajari
- <a href="https://www.selenium.dev/documentation/">Selenium documentation</a>
- <a href="https://cucumber.io/docs/cucumber/">Cucumber documentation</a>
- <a href="https://www.javadoc.io/doc/org.testng/testng/6.8.17/org/testng/Assert.html">TestNg documentation</a>
- <a href="https://gradle.org/install/">Tutorial Gradle Instalasi</a>
- <a href="https://www.youtube.com/playlist?list=PL-CtdCApEFH8yGJzfU_gners0ybO4MlrV">Gradle Tutotrial</a>
- <a href="https://www.javadoc.io/doc/org.testng/testng/6.8.17/org/testng/Assert.html">Testng Documentation</a>
