# BDD-relacionals-amb-JDBC-NBA-

## Resum

Aquest codi en Java implementa un menú interactiu per gestionar jugadors i equips en una base de dades. Utilitza una connexió JDBC per realitzar diverses operacions com llistar jugadors, calcular estadístiques, inserir nous jugadors, transferir jugadors entre equips, i més.

## Usuari i Contrasenya per la Base de Dades

- **Usuari:** root
- **Contrasenya:** (no té, espai en blanc)

## Descripció de la Classe Menu

### Variables i Objectes Estàtics

- **Colors ANSI:** Constants per donar color al text a la consola.
- **Scanner:** Objecte per llegir l'entrada de l'usuari.
- **Connection:** Connexió a la base de dades.
- **Statement i Date:** Per executar consultes SQL i manejar dates.

### Mètode `menu`

El mètode `menu` presenta un menú amb diverses opcions per gestionar jugadors i equips:

1. Llistar tots els jugadors d'un equip.
2. Calcular la mitjana de punts, rebots, assistències, etc. d'un jugador.
3. (Sense implementar) Llistar tots els partits jugats per un equip amb el seu resultat.
4. Inserir un nou jugador en un equip.
5. Transferir un jugador a un altre equip.
6. (Sense implementar) Actualitzar les dades de jugadors o equips després d'un partit.
7. Modificar les estadístiques d'un jugador.
8. Retirar (eliminar) un jugador.
9. Canviar el nom de la franquícia d'un equip.
0. Sortir.

### Mètode `llistarJugadors`

Llista tots els jugadors d'un equip especificat per l'usuari. Es executa una consulta SQL que obté els jugadors de l'equip ingressat.

### Mètode `inserirNouJugador`

Permet a l'usuari crear un nou jugador i inserir-lo a la base de dades. Recull dades com el nom, data de naixement, alçada, pes, dorsal, posició, i equip del jugador. Inclou validacions per assegurar-se que el jugador no existeix ja.

### Mètode `nouJugadorID`

Genera un nou ID per a un jugador basat en l'ID màxim existent a la taula de jugadors.

### Mètode `existeixJugador`

Comprova si un jugador ja existeix a la base de dades basant-se en el seu nom i cognom.

### Mètode `obtindreEquipID`

Obtén l'ID d'un equip donat el seu nom.

### Mètode `traspasarJugador`

Permet transferir un jugador d'un equip a un altre. Actualitza la base de dades amb el nou equip del jugador.

### Mètode `demanarDataNaixement`

Sol·licita la data de naixement del jugador i la converteix al format adequat per al seu emmagatzematge a la base de dades.

### Mètode `retirarJugador`

Retira un jugador, és a dir, l'elimina de la base de dades activa i el mou a una taula històrica, guardant les seves dades i estadístiques.

### Mètode `canviarNomFranquiciaEquip`

Permet canviar el nom de la franquícia d'un equip. Actualitza la base de dades amb el nou nom de la ciutat de l'equip.

### Mètode `existeixEquip`

Comprova si un equip existeix a la base de dades basat en el seu nom.

### Mètode `modificarEstadistiquesJugador`

Permet modificar les estadístiques d'un jugador per al seu últim partit. Sol·licita a l'usuari les noves dades i actualitza la base de dades.

### Mètode `calcularMitjanaJugador`

Calcula i mostra la mitjana de diverses estadístiques (minuts jugats, punts, rebots, etc.) d'un jugador específic, basant-se en els seus registres a la base de dades.

## Conclusió

Aquest codi és una implementació completa d'un menú de gestió de jugadors i equips, que utilitza una base de dades SQL per emmagatzemar i manipular les dades. Cada opció del menú correspon a una funció específica, proporcionant una interfície interactiva per realitzar diverses operacions de gestió.

## Informació Extra

La estructura DAODB com no tracte una BDD amb moltes dades no te una interficie general DAODB.
La clase Implementacio del Controlador es redundant.
