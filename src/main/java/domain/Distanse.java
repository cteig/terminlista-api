package domain;

public class Distanse {

    private String distanse_id;
    private String arrangement_id;
    private String distanse_lengde;
    private String distanse_starttid;
    private String distanse_startkontingent;
    private String distanse_fullforte_aaret_for;
    private String distanse_barnelop;
    private String distanse_stafett;
    private String distanse_etapper;
    private String distanse_kontrollmålt;

    public String getDistanse_id() {
        return distanse_id;
    }

    public void setDistanse_id(String distanse_id) {
        this.distanse_id = distanse_id;
    }

    public String getArrangement_id() {
        return arrangement_id;
    }

    public void setArrangement_id(String arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public String getDistanse_lengde() {
        return distanse_lengde;
    }

    public void setDistanse_lengde(String distanse_lengde) {
        this.distanse_lengde = distanse_lengde;
    }

    public String getDistanse_starttid() {
        return distanse_starttid;
    }

    public void setDistanse_starttid(String distanse_starttid) {
        this.distanse_starttid = distanse_starttid;
    }

    public String getDistanse_startkontingent() {
        return distanse_startkontingent;
    }

    public void setDistanse_startkontingent(String distanse_startkontingent) {
        this.distanse_startkontingent = distanse_startkontingent;
    }

    public String getDistanse_fullforte_aaret_for() {
        return distanse_fullforte_aaret_for;
    }

    public void setDistanse_fullforte_aaret_for(String distanse_fullforte_aaret_for) {
        this.distanse_fullforte_aaret_for = distanse_fullforte_aaret_for;
    }

    public String getDistanse_barnelop() {
        return distanse_barnelop;
    }

    public void setDistanse_barnelop(String distanse_barnelop) {
        this.distanse_barnelop = distanse_barnelop;
    }

    public String getDistanse_stafett() {
        return distanse_stafett;
    }

    public void setDistanse_stafett(String distanse_stafett) {
        this.distanse_stafett = distanse_stafett;
    }

    public String getDistanse_etapper() {
        return distanse_etapper;
    }

    public void setDistanse_etapper(String distanse_etapper) {
        this.distanse_etapper = distanse_etapper;
    }

    public String getDistanse_kontrollmålt() {
        return distanse_kontrollmålt;
    }

    public void setDistanse_kontrollmålt(String distanse_kontrollmålt) {
        this.distanse_kontrollmålt = distanse_kontrollmålt;
    }

    @Override
    public String toString() {
        return "Distanse{" +
                "distanse_id='" + distanse_id + '\'' +
                ", arrangement_id='" + arrangement_id + '\'' +
                ", distanse_lengde='" + distanse_lengde + '\'' +
                ", distanse_starttid='" + distanse_starttid + '\'' +
                ", distanse_startkontingent='" + distanse_startkontingent + '\'' +
                ", distanse_fullforte_aaret_for='" + distanse_fullforte_aaret_for + '\'' +
                ", distanse_barnelop='" + distanse_barnelop + '\'' +
                ", distanse_stafett='" + distanse_stafett + '\'' +
                ", distanse_etapper='" + distanse_etapper + '\'' +
                ", distanse_kontrollmålt='" + distanse_kontrollmålt + '\'' +
                '}';
    }
}
