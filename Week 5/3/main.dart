import 'Henkilo.dart';

main(){
  Henkilo henkilo = new Henkilo("John Smith", 30, "15 Boulevard drive");
  Henkilo henkilo2 = new Henkilo("Lee Kang", 40, "10 Somerset Street");  
  Henkilo henkilo3 = new Henkilo("Leota S. Ferrera", 44, "290 Sheila Lane");  
  Henkilo henkilo4 = new Henkilo("Marjorie V. Faith", 79, "1888 Radford Street");  
  Henkilo henkilo5 = new Henkilo("Louise J. Pals", 38, "1760 Scheuvront Drive");
/*
  henkilo.tulostaTiedot();
  henkilo2.tulostaTiedot();

print(henkilo.getNimi());

print(henkilo.getIka().toString());

print(henkilo.getOsoite());

henkilo.setNimi("Test");
henkilo.setIka(10);
henkilo.setOsoite("somewhere");

print(henkilo.getNimi());

print(henkilo.getIka().toString());

print(henkilo.getOsoite());
*/
List<Henkilo> henkiloList = [];
henkiloList.add(henkilo);
henkiloList.add(henkilo2);
henkiloList.add(henkilo3);
henkiloList.add(henkilo4);
henkiloList.add(henkilo5);
for(int i = 0; i<henkiloList.length;i++){
henkiloList[i].tulostaTiedot();
}





}