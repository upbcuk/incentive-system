package org.cryptimeleon.incetivesystem.cryptoprotocol;

import org.cryptimeleon.incentivesystem.cryptoprotocol.IncentiveSystem;
import org.cryptimeleon.incentivesystem.cryptoprotocol.model.keys.provider.ProviderPublicKey;
import org.cryptimeleon.incentivesystem.cryptoprotocol.model.keys.provider.ProviderSecretKey;
import org.cryptimeleon.incentivesystem.cryptoprotocol.model.keys.user.UserPublicKey;
import org.cryptimeleon.incentivesystem.cryptoprotocol.model.keys.user.UserSecretKey;
import org.cryptimeleon.math.serialization.converter.JSONPrettyConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyTest {

    JSONPrettyConverter jsonPrettyConverter = new JSONPrettyConverter();

    @Test
    void testProviderKeyPair() {
        var pp = IncentiveSystem.setup();
        var incentiveSystem = new IncentiveSystem(pp);

        var providerKeyPair = incentiveSystem.generateProviderKeys();

        var providerSecretKey = providerKeyPair.getSk();
        var serializedProviderSecretKey = jsonPrettyConverter.serialize(providerSecretKey.getRepresentation());
        var deserializedProviderSecretKey = new ProviderSecretKey(jsonPrettyConverter.deserialize(serializedProviderSecretKey), pp.getSpsEq(), pp.getBg().getZn(), pp.getPrf());

        assertThat(deserializedProviderSecretKey).isEqualTo(providerSecretKey);


        var providerPublicKey = providerKeyPair.getPk();
        var serializedProviderPublicKey = jsonPrettyConverter.serialize(providerPublicKey.getRepresentation());
        var deserializedProviderPublicKey = new ProviderPublicKey(jsonPrettyConverter.deserialize(serializedProviderPublicKey), pp.getSpsEq(), pp.getBg().getG1());

        assertThat(deserializedProviderPublicKey).isEqualTo(providerPublicKey);
    }

    @Test
    void testUserKeyPair() {
        var pp = IncentiveSystem.setup();
        var incentiveSystem = new IncentiveSystem(pp);

        var userKeyPair = incentiveSystem.generateUserKeys();
        var userSecretKey = userKeyPair.getSk();
        var serializedUserSecretKey = jsonPrettyConverter.serialize(userSecretKey.getRepresentation());
        var deserializedUserSecretKey = new UserSecretKey(jsonPrettyConverter.deserialize(serializedUserSecretKey), pp.getBg().getZn(), pp.getPrf());

        assertThat(deserializedUserSecretKey).isEqualTo(userSecretKey);

        var userPublicKey = userKeyPair.getPk();
        var serializedUserPublicKey = jsonPrettyConverter.serialize(userPublicKey.getRepresentation());
        var deserializedUserPublicKey = new UserPublicKey(jsonPrettyConverter.deserialize(serializedUserPublicKey), pp.getBg().getG1());

        assertThat(deserializedUserPublicKey).isEqualTo(userPublicKey);
    }
}
