/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.MemberEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class MemberDTO {
    private int id;
    private String name;
    private String studentID;
    private String favouriteColor;

    public MemberDTO(int id, String name, String studentID, String favouriteColor) {
        this.id = id;
        this.name = name;
        this.studentID = studentID;
        this.favouriteColor = favouriteColor;
    }
    
    public static List<MemberDTO> getDtos(List<MemberEntity> rms){
        List<MemberDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new MemberDTO(rm)));
        return rmdtos;
    }


    public MemberDTO(MemberEntity rm) {
        this.id = rm.getId();
        this.studentID = rm.getStudentID();
        this.name = rm.getName();
        this.favouriteColor = rm.getFavouriteColor();
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFavouriteColor() {
        return favouriteColor;
    }

    public void setFavouriteColor(String favouriteColor) {
        this.favouriteColor = favouriteColor;
    }

    @Override
    public String toString() {
        return "RenameMeDTO{" + "name=" + name + ", studentID=" + studentID + ", favouriteColor=" + favouriteColor + '}';
    }

    
    
    
    
    
}
