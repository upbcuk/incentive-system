package org.cryptimeleon.incentive.crypto.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.cryptimeleon.craco.protocols.arguments.fiatshamir.FiatShamirProof;
import org.cryptimeleon.craco.protocols.arguments.fiatshamir.FiatShamirProofSystem;
import org.cryptimeleon.craco.sig.sps.eq.SPSEQSignature;
import org.cryptimeleon.incentive.crypto.Util;
import org.cryptimeleon.incentive.crypto.proof.SpendDeductZkpCommonInput;
import org.cryptimeleon.math.serialization.ListRepresentation;
import org.cryptimeleon.math.serialization.Representable;
import org.cryptimeleon.math.serialization.Representation;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.groups.cartesian.GroupElementVector;
import org.cryptimeleon.math.structures.rings.zn.Zn;

import java.math.BigInteger;

/**
 * Data class for the request sent by a user in spend-deduct.
 */
@Value
@AllArgsConstructor
public class SpendRequest implements Representable {
    @NonFinal
    GroupElement dsid;

    @NonFinal
    FiatShamirProof spendDeductZkp;

    @NonFinal
    Zn.ZnElement c0;

    @NonFinal
    Zn.ZnElement c1;

    @NonFinal
    GroupElement cPre0;

    @NonFinal
    GroupElement cPre1;

    @NonFinal
    GroupElementVector cTrace0;

    @NonFinal
    GroupElementVector cTrace1;

    @NonFinal
    GroupElement commitmentC0; // Dont sent C_1 since it should be equal to g_1 anyways

    @NonFinal
    SPSEQSignature sigma;

    public SpendRequest(Representation repr, IncentivePublicParameters pp, FiatShamirProofSystem fiatShamirProofSystem, BigInteger k, Zn.ZnElement tid) {
        var listRepr = repr.list();
        var zn = pp.getBg().getZn();
        var groupG1 = pp.getBg().getG1();
        var groupG2 = pp.getBg().getG2();

        this.dsid = groupG1.restoreElement(listRepr.get(0));
        this.c0 = zn.restoreElement(listRepr.get(1));
        this.c1 = zn.restoreElement(listRepr.get(2));
        this.cPre0 = groupG1.restoreElement(listRepr.get(3));
        this.cPre1 = groupG1.restoreElement(listRepr.get(4));
        this.commitmentC0 = groupG1.restoreElement(listRepr.get(5));
        this.cTrace0 = groupG1.restoreVector(listRepr.get(6));
        this.cTrace1 = groupG1.restoreVector(listRepr.get(7));

        var gamma = Util.hashGamma(zn, k, dsid, tid, cPre0, cPre1);
        var spendDeductCommonInput = new SpendDeductZkpCommonInput(k, gamma, c0, c1, dsid, cPre0, cPre1, commitmentC0, cTrace0, cTrace1);
        this.spendDeductZkp = fiatShamirProofSystem.restoreProof(spendDeductCommonInput, listRepr.get(8));
        this.sigma = new SPSEQSignature(listRepr.get(9), groupG1, groupG2);
    }

    @Override
    public Representation getRepresentation() {
        return new ListRepresentation(
                dsid.getRepresentation(),
                c0.getRepresentation(),
                c1.getRepresentation(),
                cPre0.getRepresentation(),
                cPre1.getRepresentation(),
                commitmentC0.getRepresentation(),
                cTrace0.getRepresentation(),
                cTrace1.getRepresentation(),
                spendDeductZkp.getRepresentation(),
                sigma.getRepresentation()
        );
    }
}
