class Henkilo{

  Henkilo(this._nimi, this._ika, this._osoite){
    print("Henkilo constructor");
  }

  String _nimi;
  int _ika;
  String _osoite;

  void setNimi(String aNimi){
    this._nimi = aNimi;
  }
  void setIka(int aIka){
    this._ika = aIka;
  }
  void setOsoite(String aOsoite){
    this._osoite = aOsoite;
  }

  String getNimi(){
    return this._nimi;
  }
  int getIka(){
    return this._ika;
  }
  String getOsoite(){
    return this._osoite;
  }


  void tulostaTiedot(){
    print("Name: " + this._nimi + ", Age: " + this._ika.toString() + ", Adress: " + this._osoite);
  }

}