package finalsoft.FcmService.Models;


/**
 * Created by Pouya on 6/14/2016.
 */
public class Theme {
    public static final String NORMAL="normal";
    public static final String BLUE="blue";
    public static final String GREY="grey";
    public static final String SELECTED="selected";
    private int id;
    private String name;
    private int actionbarcolor;
    private int tabLayoutColor;
    private int Floatingbuttondrawble;

    public Theme(int id, String name, int actionbarcolor, int tabLayoutColor, int floatingbuttondrawble) {
        this.id = id;
        this.name = name;
        this.actionbarcolor = actionbarcolor;
        this.tabLayoutColor = tabLayoutColor;
        Floatingbuttondrawble = floatingbuttondrawble;

    }

    public Theme(int id, String name, int actionbarcolor, int tabLayoutColor, int floatingbuttondrawble,  String icontheme,String iconthemeselected) {
        this.id = id;
        this.name = name;
        this.actionbarcolor = actionbarcolor;
        this.tabLayoutColor = tabLayoutColor;
        Floatingbuttondrawble = floatingbuttondrawble;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActionbarcolor() {
        return actionbarcolor;
    }

    public void setActionbarcolor(int actionbarcolor) {
        this.actionbarcolor = actionbarcolor;
    }

    public int getTabLayoutColor() {
        return tabLayoutColor;
    }

    public void setTabLayoutColor(int tabLayoutColor) {
        this.tabLayoutColor = tabLayoutColor;
    }

    public int getFloatingbuttondrawble() {
        return Floatingbuttondrawble;
    }

    public void setFloatingbuttondrawble(int floatingbuttondrawble) {
        Floatingbuttondrawble = floatingbuttondrawble;
    }

}
