package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "identity")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Identity extends Auditable {

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "mbox_sha1sum")
    private String mboxSha1Sum;

    @Column(name = "mbox")
    private String mbox;

    @Column(name = "openid")
    private String openid;

    @Column(name = "home_page")
    private String homePage;

    @Column(name = "name")
    private String name;

    @Column(name = "ifi", nullable = false, length = 255, updatable = false,
        insertable = false)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String ifi;

    /**
    * Static method to return an xAPI Inverse Functional Identifier for actor
    * properties.
    * Should work for any incoming actor to help lookup existing identities.
    *
    * @param  mboxSha1Sum  mboxsha1sum property of actor
    * @param  mbox         mbox property of actor
    * @param  openid       openid property of actor
    * @param  homePage     account.homePage property of actor
    * @param  name         account.name property of actor
    * @return              the Inverse Functional Identifier
    */
    @Transient
    public static String createIfi(String mboxSha1Sum, String mbox,
                    String openid, String homePage, String name) {
        final String ifiTemplate = "%s::%s";
        final String accountTemplate = "%s@%s";
        if (mboxSha1Sum != null) {
            return String.format(ifiTemplate, "mbox_sha1sum", mboxSha1Sum);
        } else if (openid != null) {
            return String.format(ifiTemplate, "openid", openid);
        } else if (homePage != null) {
            return String.format(ifiTemplate, "account",
                    String.format(accountTemplate, name, homePage));
        } else {
            return String.format(ifiTemplate, "mbox", mbox);
        }
    }

    /**
    * Uses createIfi to form an IFI from existing Identity properties.
    *
    * @return the current Inverse Functional Identifier
    */
    public String getIfi() {
        return createIfi(mboxSha1Sum, mbox, openid, homePage, name);
    }

    @Override
    public String toString() {
        String personValue =  (person != null)
            ? person.getId().toString() : null;
        return "Identity [person=" + personValue + ", mboxSha1Sum="
        + mboxSha1Sum + ", id=" + id + ", mbox=" + mbox + ", openid=" + openid
        + ", homePage=" + homePage + ", name = " + name + ", ifi=" + getIfi()
        + "]";
    }

}
