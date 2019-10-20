<H2>Alkalmazások fejlesztése beadandó</H2>
<H3>Bevezető</H3>
<p>Az alapötlet a neptun mintájára épülő, az egyetem szereplői számára adminisztrációs feladatok(tantárgyak felvétele/menedzselése tanároknak/diákoknak) kezelésére alkalmas program létrehozása.</p>
<H3>Projektötlet</H3>
<H4>Funkcionális követelmények</H4>
<ul>
  <li>Regisztráció</li>
  <li>Bejelentkezés</li>
  <li>Bejelentkezett felhasználóknak
    <ul>
      <li>Adminnak
        <ul>
          <li>Kurzus hozzáadása/módosítása/törlése</li>
          <li>Vizsga hozzáadása/módosítása/törlése</li>
          <li>Tanári jog adása/elvétele</li>          
          <li>Felhasználók kilistázása/törlése</li>
        </ul>
      </li>
      <li>Tanárnak</li>
        <ul>
          <li>Saját kurzusok módosítása/törlése/megjelenítése órarendben</li> 
          <li>Saját vizsgák módosítása/törlése/megjelenítése órarendben</li> 
          <li>Diák törlése saját kurzusról/vizsgáról</li>
          <li>Saját kurzushoz/vizsgához tartozó diák számára jegy adása</li>
        </ul>
      <li>Diáknak</li>
        <ul>
          <li>Kurzus felvétele/leadása</li>
          <li>Vizsga felvétele/leadása</li>
          <li>Felvett kurzusok részletes kilistázása, megjelenítése órarendben</li>
        </ul>
      <li>Általánosan</li>
        <ul>
          <li>Saját adatok megjelenítése/módosítása</li>
          <li>E-mail küldése</li>
          <li>E-mailek kilistázása</li>
          <li>Kurzusok/Vizsgák kilistázása</li>
        </ul>
    </ul>
  </li>
</ul>
<H4>Nem funkcionális követelmények</H4>
<ul>
  <li>Felhasználóbarát: Megfelelően elhatárolt funkciók. Világos látható színekkel írt betűk. Ésszerű elrendezés.</li>
  <li>Biztonság: Jelszóval védett funkciók. A jelszavak titkosítottak. A különböző űrlapoknál egy hibalistában kijelzi a program a hibákat.</li>
  <li>Gyors működés: Adatbázisban operáló program, gyors kereséssekkel hamar előállítja a kívánt eredményeket.</li>
</ul>
<H4>Szakterületi fogalomjegyzék</H4>
<ul>
  <li>Kurzus: az a keret, amelyben a hallgatók meghatározott rend (előadás, gyakorlat, házi feladat, stb.) szerint gyarapítják tudásukat, és arról számot is adnak.</li>
  <li>Vizsga: tudás számonkérése arról, hogy egy tanuló a kurzus során megtanult anyagot mennyire értette meg, és hogyan tudja alkalmazni. </li>
  <li>E-mail: (ejtsd: ímél) elektronikus levél, a postához hasonló elektronikus üzenetkézbesítési szolgáltatás által továbbított üzenet</li>
  <li>Admin: egy adott rendszer felügyeletét ellátó, afelett általában teljes kontrollal rendelkező felhasználó, ill. az azt megszemélyesítő azonosító.</li>
</ul>
<H4>Szerepkörök</H4>
<ul>
  <li>Vendég: nem regisztrált látogató, aki csak a kezdőoldalt tudja megtekinteni, ahol csak regisztrációra/belépésre képes.</li>
  <li>Diák: lásd funkcionális követelmények</li>
  <li>Tanár: lásd funkcionális követelmények</li>
  <li>Admin: lásd funkcionális követelmények</li>
</ul>
<H3>Projektötlet</H3>
<H4>Entitások</H4>
<ul>
  <li>Student
    <ul>
      <li>Id (Long)</li>
      <li>NeptunCode (String)</li>
      <li>Password (String)</li>
      <li>Name (String)</li>
      <li>Courses (List&lt;Course&gt;)</li>
      <li>Exams (List&lt;Exam&gt;)</li>
    </ul>
  </li>
  <li>Teacher
    <ul>
      <li>Id (Long)</li>
      <li>NeptunCode (String)</li>
      <li>Password (String)</li>
      <li>Name (String)</li>
      <li>Courses (List&lt;Course&gt;)</li>
      <li>Exams (List&lt;Exam&gt;)</li>
    </ul>
  </li>
  <li>Course
    <ul>
        <li>Id (Long)</li>
        <li>Name (String)</li>
        <li>MaxLimit (Integer)</li>
        <li>ClassRoom (String)</li>
        <li>Date (String)</li>
        <li>Teacher (Teacher)</li>
        <li>Students (List&lt;Student&gt;)</li>
    </ul>
  </li>
  <li>Exam
    <ul>
        <li>Id (Long)</li>
        <li>Name (String)</li>
        <li>MaxLimit (Integer)</li>
        <li>ClassRoom (String)</li>
        <li>Date (String)</li>
        <li>Teacher (Teacher)</li>
        <li>Students (List&lt;Student&gt;)</li>
    </ul>
  </li>
</ul>

<H4>Végpontok</H4>
<ul>
  <li>GET/
    <ul>
      <li>/courses: Kurzusok megjelenítése
         <ul>
           <li>/:id: Adott id-hez tartozó kurzus megjelenítése</li>
           <li>/:id/students: Adott id-hez tartozó kurzus diákjainak megjelenítése</li>
           <li>/:id/teacher: Adott id-hez tartozó kurzus tanárának megjelenítése</li>
         </ul>
      </li>
      <li>/exams: Vizsgák megjelenítése
         <ul>
           <li>/:id: Adott id-hez tartozó vizsga megjelenítése</li>
           <li>/:id/students: Adott id-hez tartozó vizsga diákjainak megjelenítése</li>
           <li>/:id/teacher: Adott id-hez tartozó vizsga tanárának megjelenítése</li>
         </ul>
      </li>
      <li>/teachers: Tanárok megjelenítése
         <ul>
           <li>/:id: Adott id-hez tartozó tanár megjelenítése</li>
           <li>/:id/courses: Adott id-hez tartozó tanár kurzusainak megjelenítése</li>
           <li>/:id/exams: Adott id-hez tartozó tanár vizsgáinak megjelenítése</li>
         </ul>
      </li>
      <li>/students: Diákok megjelenítése
         <ul>
           <li>/:id: Adott id-hez tartozó diák megjelenítése</li>
           <li>/:id/courses: Adott id-hez tartozó diák kurzusainak megjelenítése</li>
           <li>/:id/exams: Adott id-hez tartozó diák vizsgáinak megjelenítése</li>
         </ul>
      </li>
    </ul>
  </li>
</ul>
<H4>Könyvtárstruktúra</H4>
