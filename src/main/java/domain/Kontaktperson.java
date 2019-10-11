package domain;

public class Kontaktperson {

    private String kontaktperson_id;
    private String arrangement_id;
    private String navn;
    private String telefon;
    private String mobil;
    private String epost;

    public String getKontaktperson_id() {
        return kontaktperson_id;
    }

    public void setKontaktperson_id(String kontaktperson_id) {
        this.kontaktperson_id = kontaktperson_id;
    }

    public String getArrangement_id() {
        return arrangement_id;
    }

    public void setArrangement_id(String arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    @Override
    public String toString() {
        return "Kontaktperson{" +
                "kontaktperson_id='" + kontaktperson_id + '\'' +
                ", arrangement_id='" + arrangement_id + '\'' +
                ", navn='" + navn + '\'' +
                ", telefon='" + telefon + '\'' +
                ", mobil='" + mobil + '\'' +
                ", epost='" + epost + '\'' +
                '}';
    }
}
