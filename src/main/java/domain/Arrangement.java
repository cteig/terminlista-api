package domain;

import java.util.List;

public class Arrangement {

    private String arrangement_id;
    private String overskrift;
    private String arrangør;
    private String arrangert;
    private String adresse;
    private String postnr;
    private String poststed;
    private String land;
    private String fylke;
    private String kommune;
    private String dato;
    private List<Kontaktperson> kontaktpersonList;
    private String start;
    private String kart;
    private String mål;
    private String parkering;
    private String garderobe;
    private String etteranmeldingsgebyr;
    private String kontonummer;
    private String påmelding;
    private String påmelding_postadresse;
    private String påmelding_postnr;
    private String påmelding_poststed;
    private String kvinneløp;
    private String motbakkeløp;
    private String nfif_lisens;
    private String inngår_i;
    private String link;
    private List<Distanse> distanseList;
    private String underlag;
    private String underlag_kommentar;
    private String hjemmesider;
    private String premier;
    private String kommentar;
    private String klasseinndeling;
    private String beskrivelse;

    public String getOverskrift() {
        return overskrift;
    }

    public void setOverskrift(String overskrift) {
        this.overskrift = overskrift;
    }

    public String getArrangør() {
        return arrangør;
    }

    public void setArrangør(String arrangør) {
        this.arrangør = arrangør;
    }

    public String getArrangert() {
        return arrangert;
    }

    public void setArrangert(String arrangert) {
        this.arrangert = arrangert;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getArrangementId() {
        return arrangement_id;
    }

    public void setArrangementId(String arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getFylke() {
        return fylke;
    }

    public void setFylke(String fylke) {
        this.fylke = fylke;
    }

    public String getKommune() {
        return kommune;
    }

    public void setKommune(String kommune) {
        this.kommune = kommune;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public List<Kontaktperson> getKontaktpersonList() {
        return kontaktpersonList;
    }

    public void setKontaktpersonList(List<Kontaktperson> kontaktpersonList) {
        this.kontaktpersonList = kontaktpersonList;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getKart() {
        return kart;
    }

    public void setKart(String kart) {
        this.kart = kart;
    }

    public String getMål() {
        return mål;
    }

    public void setMål(String mål) {
        this.mål = mål;
    }

    public String getParkering() {
        return parkering;
    }

    public void setParkering(String parkering) {
        this.parkering = parkering;
    }

    public String getGarderobe() {
        return garderobe;
    }

    public void setGarderobe(String garderobe) {
        this.garderobe = garderobe;
    }

    public String getEtteranmeldingsgebyr() {
        return etteranmeldingsgebyr;
    }

    public void setEtteranmeldingsgebyr(String etteranmeldingsgebyr) {
        this.etteranmeldingsgebyr = etteranmeldingsgebyr;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getArrangement_id() {
        return arrangement_id;
    }

    public void setArrangement_id(String arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public String getPåmelding() {
        return påmelding;
    }

    public void setPåmelding(String påmelding) {
        this.påmelding = påmelding;
    }

    public String getPåmelding_postadresse() {
        return påmelding_postadresse;
    }

    public void setPåmelding_postadresse(String påmelding_postadresse) {
        this.påmelding_postadresse = påmelding_postadresse;
    }

    public String getPåmelding_postnr() {
        return påmelding_postnr;
    }

    public void setPåmelding_postnr(String påmelding_postnr) {
        this.påmelding_postnr = påmelding_postnr;
    }

    public String getPåmelding_poststed() {
        return påmelding_poststed;
    }

    public void setPåmelding_poststed(String påmelding_poststed) {
        this.påmelding_poststed = påmelding_poststed;
    }

    public String getKvinneløp() {
        return kvinneløp;
    }

    public void setKvinneløp(String kvinneløp) {
        this.kvinneløp = kvinneløp;
    }

    public String getMotbakkeløp() {
        return motbakkeløp;
    }

    public void setMotbakkeløp(String motbakkeløp) {
        this.motbakkeløp = motbakkeløp;
    }

    public String getNfif_lisens() {
        return nfif_lisens;
    }

    public void setNfif_lisens(String nfif_lisens) {
        this.nfif_lisens = nfif_lisens;
    }

    public String getInngår_i() {
        return inngår_i;
    }

    public void setInngår_i(String inngår_i) {
        this.inngår_i = inngår_i;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Distanse> getDistanseList() {
        return distanseList;
    }

    public void setDistanseList(List<Distanse> distanseList) {
        this.distanseList = distanseList;
    }

    public String getUnderlag() {
        return underlag;
    }

    public void setUnderlag(String underlag) {
        this.underlag = underlag;
    }

    public String getUnderlag_kommentar() {
        return underlag_kommentar;
    }

    public void setUnderlag_kommentar(String underlag_kommentar) {
        this.underlag_kommentar = underlag_kommentar;
    }

    public String getHjemmesider() {
        return hjemmesider;
    }

    public void setHjemmesider(String hjemmesider) {
        this.hjemmesider = hjemmesider;
    }

    public String getPremier() {
        return premier;
    }

    public void setPremier(String premier) {
        this.premier = premier;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getKlasseinndeling() {
        return klasseinndeling;
    }

    public void setKlasseinndeling(String klasseinndeling) {
        this.klasseinndeling = klasseinndeling;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    @Override
    public String toString() {
        return "Arrangement{" +
                "arrangement_id='" + arrangement_id + '\'' +
                ", overskrift='" + overskrift + '\'' +
                ", arrangør='" + arrangør + '\'' +
                ", arrangert='" + arrangert + '\'' +
                ", adresse='" + adresse + '\'' +
                ", postnr='" + postnr + '\'' +
                ", poststed='" + poststed + '\'' +
                ", land='" + land + '\'' +
                ", fylke='" + fylke + '\'' +
                ", kommune='" + kommune + '\'' +
                ", dato='" + dato + '\'' +
                ", kontaktpersonList=" + kontaktpersonList +
                ", start='" + start + '\'' +
                ", kart='" + kart + '\'' +
                ", mål='" + mål + '\'' +
                ", parkering='" + parkering + '\'' +
                ", garderobe='" + garderobe + '\'' +
                ", etteranmeldingsgebyr='" + etteranmeldingsgebyr + '\'' +
                ", kontonummer='" + kontonummer + '\'' +
                ", påmelding='" + påmelding + '\'' +
                ", påmelding_postadresse='" + påmelding_postadresse + '\'' +
                ", påmelding_postnr='" + påmelding_postnr + '\'' +
                ", påmelding_poststed='" + påmelding_poststed + '\'' +
                ", kvinneløp='" + kvinneløp + '\'' +
                ", motbakkeløp='" + motbakkeløp + '\'' +
                ", nfif_lisens='" + nfif_lisens + '\'' +
                ", inngår_i='" + inngår_i + '\'' +
                ", link='" + link + '\'' +
                ", distanseList=" + distanseList +
                ", underlag='" + underlag + '\'' +
                ", underlag_kommentar='" + underlag_kommentar + '\'' +
                ", hjemmesider='" + hjemmesider + '\'' +
                ", premier='" + premier + '\'' +
                ", kommentar='" + kommentar + '\'' +
                ", klasseinndeling='" + klasseinndeling + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                '}';
    }
}