In dieser Aufgabe üben Sie nochmal Arrays, um aus den letzte Woche implementierten Signalberechnungen eine tatsächlich funktionierende MIDI-Datei zu erstellen. In der Klasse `MIDIexample` finden Sie wieder den nötigen Code, um aus Ihrem Array eine Datei zu machen.


## Aufgabe 1

Implementieren Sie eine Methode `getHeader(byte speed)`, die ein `byte[]` mit dem MIDI-Header zurückgibt. Dieser soll am Anfang die folgenden Bytes (Hexadezimal-Werte) enthalten:

4D 54 68 64 00 00 00 06 00 00 00 01 00

Dahinter soll das letzte Byte die übergebene Geschwindigkeit sein (`speed`).

Bedenken Sie, dass Sie Hexadezimalzahlen nicht von Hand in Dezimalzahlen umrechnen müssen:

```java
int x = 68; // Das ist der Wert 68 in Dezimal
int y = 0x68; // Durch das 0x davor wird signalisiert, dass der Wert in Hexadezimal angegeben wurde - es ist also der Wert 104 im Dezimalsystem.
```

## Aufgabe 2

Implementieren Sie eine Methode `addNoteToTrack(byte[] trackdata, byte[] noteEvent)`, die existierende Track-Daten als `trackdata` sowie ein neues Noten-Event als `noteEvent` bekommt und ein neues `byte[]` zurückgibt, welches sowohl die existierenden Daten als auch dahinter angehängt die Daten aus dem Noten-Event enthält. Bedenken Sie, dass Sie die Länge eines Arrays nicht verändern können - Sie benötigen für das Ergebnis ein neues Array!

## Aufgabe 3

Implementieren Sie eine Methode `getTrack(byte instrument, byte[] trackdata)`, welche einen Instrumentcode und Trackdaten erhält und ein `byte[]` mit dem kompletten Track zurückgibt. Der Track ist wie folgt aufgebaut (alle Werte gebe ich hier hexadezimal an):

* 4D 54 72 6B 00 00 00: Header
* Ein Byte Länge (die Anzahl der Byte, die nach dem Header und diesem Längen-Byte noch kommen, ohne die letzten 3 Byte die das Trackende markieren)
* 00 FF 58 04 04 02 18 08 00 FF 51 03 07 A1 20: Geschwindigkeitsinformationen, darum kümmern wir uns zunächst nicht - Sie können das einfach so direkt übernehmen
* 00 C0: Setzen des Instruments auf Channel 0.
* Ein Byte Instrumentcode (das Instrument, welches verwendet werden soll)
* Die Track-Daten (in unserem Fall lauter Noten-Events)
* FF 2F 00: Ende des Tracks (werden bei der Längenangabe im Header nicht mit berücksichtigt)

Hinweis: Sie müssen hier immer wieder Byte-Arrays miteinenader verketten. Eventuell haben Sie schon eine Methode in dieser Hausaufgabe implementiert, die Sie dafür wiederverwenden können?
