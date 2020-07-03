package com.PPE4.gsb.objects;

public class User {
    private int _id;
    private String _nom;
    private String _prenom;
    private int _adminLevel;

    public User(int id, String nom, String prenom, int adminLevel) {
        this._id = id;
        this._nom = nom;
        this._prenom = prenom;
        this._adminLevel = adminLevel;
    }

    public int get_id() {
        return _id;
    }

    public String get_nom() {
        return _nom;
    }

    public String get_prenom() {
        return _prenom;
    }

    public int get_adminLevel() {
        return _adminLevel;
    }
}
