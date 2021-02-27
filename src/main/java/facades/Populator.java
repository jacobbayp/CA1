/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MemberDTO;
import entities.MemberEntity;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MemberFacade fe = MemberFacade.getMemberFacade(emf);
        fe.create(new MemberDTO(new MemberEntity("Jacob", "cph-jp385","blå")));
        fe.create(new MemberDTO(new MemberEntity("Jonas", "cph-jj467","rød")));
        fe.create(new MemberDTO(new MemberEntity("Casper", "cph-ct139","grøn")));
        
    }
    
    public static void main(String[] args) {
        populate();
    }
}
