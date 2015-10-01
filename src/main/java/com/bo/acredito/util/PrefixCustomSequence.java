package com.bo.acredito.util;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sessions.DatabaseRecord;
import org.eclipse.persistence.sessions.Session;

import java.util.UUID;
import java.util.Vector;

/**
 * Created by alvaro on 28/7/15.
 */
public class PrefixCustomSequence extends Sequence implements SessionCustomizer {
    private String prefix;

    public PrefixCustomSequence(String name, String prefix) {
        super(name);
        this.prefix=prefix;
    }

    @Override
    public Object getGeneratedValue(Accessor accessor,
                                    AbstractSession writeSession, String seqName) {
        /*System.out.println("************ SEQ NAME: " + seqName);
        Vector result=writeSession.executeSQL("Select SEQ_COUNT from SEQUENCE WHERE SEQ_NAME='"+seqName+"'");
        Long value= (Long)((DatabaseRecord)result.get(0)).getValues("SEQ_COUNT");
        System.out.println("*********** NEXT VALUE: " + value);
        writeSession.executeNonSelectingSQL("UPDATE SEQUENCE SET SEQ_COUNT=SEQ_COUNT+1 WHERE SEQ_NAME='"+seqName+"'");*/

        return prefix+"_"+UUID.randomUUID().toString();
    }

    @Override
    public Vector getGeneratedVector(Accessor accessor, AbstractSession writeSession, String seqName, int size) {
        return null;
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisconnect() {

    }


    @Override
    public boolean shouldAcquireValueAfterInsert() {
        return false;
    }


    @Override
    public boolean shouldUseTransaction() {
        return false;
    }

    @Override
    public boolean shouldUsePreallocation() {
        return false;
    }

    public void customize(Session session) throws Exception {
        String idPrefix= (String) session.getProperty("com.bo.acredito.idPrefix");
        PrefixCustomSequence sequence = new PrefixCustomSequence("prefix-custom",idPrefix);
        session.getLogin().addSequence(sequence);

    }

}
