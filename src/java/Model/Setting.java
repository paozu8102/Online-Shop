/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class Setting {
     private int id;
    private String name;
        private String settingtype;
    private int status;

    public Setting() {
    }

    public Setting(int id, String name, String settingtype, int status) {
        this.id = id;
        this.name = name;
        this.settingtype = settingtype;
        this.status = status;
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

    public String getSettingtype() {
        return settingtype;
    }

    public void setSettingtype(String settingtype) {
        this.settingtype = settingtype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

}
