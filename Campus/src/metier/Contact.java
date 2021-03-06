/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import utils.LogUtils;

/**
 *
 * @author biron
 */
public class Contact {

    private AbstractUser utilisateur1;
    private AbstractUser utilisateur2;

    public Contact(AbstractUser pUtilisateur1, AbstractUser pUtilisateur2) {
        if (!pUtilisateur1.getClass().isInstance(Administrator.class)
                && !pUtilisateur2.getClass().isInstance(Administrator.class)) {
            this.utilisateur1 = pUtilisateur1;
            this.utilisateur2 = pUtilisateur2;
            LogUtils.addLog(utilisateur1.getLogin(), "Ajout d'un Contact[id='" + utilisateur2.getId()
                    + "'; login='" + utilisateur2.getLogin() + "'].");
        }
    }

    public AbstractUser getUtilisateur1() {
        return utilisateur1;
    }

    public void setUtilisateur1(AbstractUser utilisateur1) {
        this.utilisateur1 = utilisateur1;
    }

    public AbstractUser getUtilisateur2() {
        return utilisateur2;
    }

    public void setUtilisateur2(AbstractUser utilisateur2) {
        this.utilisateur2 = utilisateur2;
    }

}
