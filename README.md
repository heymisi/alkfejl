# <H2>Alkalmazások fejlesztése beadandó</H2>
<H3>Bevezető</H3>
<p>Az alapötlet egy diákok számára tantárgyak felvévésére és a tanárok számára a tárgyak menedzselésére alkalmas program.</p>
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
