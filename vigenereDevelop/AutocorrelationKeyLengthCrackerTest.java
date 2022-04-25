package space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.keylen.autocorrelation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutocorrelationKeyLengthCrackerTest {

    @Test
    void returns_key_length_with_highest_autocorrelation() {
        var ciphertext =
                "СГУЧПВЭЛЛЗИРТЕПНДНФГИНБОРГЙУГЛЧД" +
                "КОТХЖГУУМЗДХРЪСГСЮДТПЪАРВЙГГИЩВЧ" +
                "ЭЕЦСТУЖВСЕВХАХЯФБЬБЕТФЗСЭФТХЖЗБЗ" +
                "ЪГФБЩИХХРИПЖТЗВТЖЙТГОЙБНТФФЕОИХТ" +
                "ТЕГИИОКЗПТФЛЕУГСФИПТЬМОФОКСХМГБТ" +
                "ЖФЫГУЧОЮНФНШЗГЭЛЛШРУДЕНКОЛГГНСБК" +
                "ССЕУПНФЦЕЕЕГГСЖНОЕЫИОНРСИТКЦЬЕДБ" +
                "УБТЕТЛОТБФЦСБЮЙПМПЗТЖПТУФКДГ";

        int estimatedKeyLength = new AutocorrelationKeyLengthCracker().crackKeyLength(ciphertext);
        assertEquals(6, estimatedKeyLength);
    }
}
