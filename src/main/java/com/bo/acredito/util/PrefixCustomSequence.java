package com.bo.acredito.util;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.TableSequence;
import org.eclipse.persistence.sessions.Session;

import java.util.Vector;

/**
 * Created by alvaro on 28/7/15.
 */
public class PrefixCustomSequence extends TableSequence implements SessionCustomizer {
    private String prefix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public PrefixCustomSequence() {
        super();
    }



    @Override
    public Vector getGeneratedVector(Accessor accessor, AbstractSession writeSession, String seqName, int size) {
        Vector vector=super.getGeneratedVector(accessor, writeSession, seqName, size);
        Vector newVector=new Vector();
        for (Object o : vector) {
            newVector.add(prefix+"_"+String.valueOf(o));
        }
        return newVector;
    }

    public void customize(Session session) throws Exception {
        String idPrefix= (String) session.getProperty("com.bo.acredito.idPrefix");
        PrefixCustomSequence sequence = new PrefixCustomSequence();
        sequence.setPrefix(idPrefix);
        session.getLogin().setDefaultSequence(sequence);
    }
}
