package thi.dat65130430.onthi_bottomnavigationmenu;

public class LandScape {
    private String landscapeName;
    private String landscapeImage;

    public LandScape(String landscapeName, String landscapeImage) {
        this.landscapeName = landscapeName;
        this.landscapeImage = landscapeImage;
    }

    public String getLandscapeName() {
        return landscapeName;
    }

    public void setLandscapeName(String landscapeName) {
        this.landscapeName = landscapeName;
    }

    public String getLandscapeImage() {
        return landscapeImage;
    }

    public void setLandscapeImage(String landscapeImage) {
        this.landscapeImage = landscapeImage;
    }
}