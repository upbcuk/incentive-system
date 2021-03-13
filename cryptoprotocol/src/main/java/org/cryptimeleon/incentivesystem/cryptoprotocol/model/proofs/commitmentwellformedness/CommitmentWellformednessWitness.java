package org.cryptimeleon.incentivesystem.cryptoprotocol.model.proofs.commitmentwellformedness;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.math.structures.rings.zn.Zn.ZnElement;

@Value
@AllArgsConstructor
public class CommitmentWellformednessWitness implements SecretInput {
    private final ZnElement usk; // user secret key
    private final ZnElement eskUsr;
    private final ZnElement dsrnd0;
    private final ZnElement dsrnd1;
    private final ZnElement z;
    private final ZnElement t;
    private final ZnElement uInverse;
}
