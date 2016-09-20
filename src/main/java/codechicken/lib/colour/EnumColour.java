package codechicken.lib.colour;

import codechicken.lib.util.TripleABC;
import com.google.common.collect.ImmutableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by covers1624 on 16/09/2016.
 */
public enum EnumColour {

    WHITE("white", "dyeWhite", "item.fireworksCharge.white", 0xFFFFFF),
    ORANGE("orange", "dyeOrange", "item.fireworksCharge.orange", 0xC06300),
    MAGENTA("magenta", "dyeMagenta", "item.fireworksCharge.magenta", 0xB51AB5),
    LIGHT_BLUE("light_blue", "dyeLightBlue", "item.fireworksCharge.lightBlue", 0x6F84F1),
    YELLOW("yellow", "dyeYellow", "item.fireworksCharge.yellow", 0xBFBF00),
    LIME("lime", "dyeLime", "item.fireworksCharge.lime", 0x6BF100),
    PINK("pink", "dyePink", "item.fireworksCharge.pink", 0xF14675),
    GRAY("gray", "dyeGray", "item.fireworksCharge.gray", 0x535353),
    LIGHT_GRAY("light_gray", "dyeLightDray", "item.fireworksCharge.silver", 0x939393),
    CYAN("cyan", "dyeCyan", "item.fireworksCharge.cyan", 0x008787),
    PURPLE("purple", "dyePurple", "item.fireworksCharge.purple", 0x5E00C0),
    BLUE("blue", "dyeBlue", "item.fireworksCharge.blue", 0x1313C0),
    BROWN("brown", "dyeBrown", "item.fireworksCharge.brown", 0x4F2700),
    GREEN("green", "dyeGreen", "item.fireworksCharge.green", 0x088700),
    RED("red", "dyeRed", "item.fireworksCharge.red", 0xA20F06),
    BLACK("black", "dyeBlack", "item.fireworksCharge.black", 0x1F1F1F);

    private String minecraftName;
    private String oreDictionaryName;
    private String unlocalizedName;
    private int rgb;

    private static final ImmutableList<TripleABC<EnumColour, EnumColour, EnumColour>> mixMap;

    EnumColour(String minecraftName, String oreDictionaryName, String unlocalizedName, int rgb) {
        this.minecraftName = minecraftName;
        this.oreDictionaryName = oreDictionaryName;
        this.unlocalizedName = unlocalizedName;
        this.rgb = rgb;
    }

    public String getMinecraftName() {
        return minecraftName;
    }

    public String getOreDictionaryName() {
        return oreDictionaryName;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public EnumColour mix(EnumColour b) {
        return mix(this, b);
    }

    public int rgba() {
        return rgba(0xFF);
    }

    public int rgba(int alpha) {
        return rgb << 8 | (alpha & 0xFF);
    }

    public int argb() {
        return argb(0xFF);
    }

    public int argb(int alpha) {
        return (alpha & 0xFF) << 24 | rgb;
    }

    public ColourRGBA getColour() {
        return getColour(0xFF);
    }

    public ColourRGBA getColour(int alpha) {
        return new ColourRGBA(rgba(alpha));
    }

    public static EnumColour mix(EnumColour a, EnumColour b) {
        if (a == b) {
            return a;
        }
        synchronized (mixMap) {
            for (TripleABC<EnumColour, EnumColour, EnumColour> triple : mixMap) {
                if (triple.getA().equals(a) && triple.getB().equals(b)) {
                    return triple.getC();
                }
            }
        }
        return null;
    }

    static {
        ImmutableList.Builder<TripleABC<EnumColour, EnumColour, EnumColour>> builder = ImmutableList.builder();
        //WHITE
        builder.add(getTriple(YELLOW, RED, ORANGE));
        builder.add(getTriple(PINK, PURPLE, MAGENTA));
        builder.add(getTriple(WHITE, BLUE, LIGHT_BLUE));
        //YELLOW
        builder.add(getTriple(WHITE, GREEN, LIME));
        builder.add(getTriple(WHITE, RED, PINK));
        builder.add(getTriple(WHITE, BLACK, GRAY));
        builder.add(getTriple(WHITE, GRAY, LIGHT_GRAY));
        builder.add(getTriple(BLUE, GREEN, CYAN));
        builder.add(getTriple(BLUE, RED, PURPLE));
        //Blue
        builder.add(getTriple(ORANGE, RED, BROWN));
        builder.add(getTriple(YELLOW, BLUE, GREEN));
        //RED
        //BLACK

        mixMap = builder.build();
    }

    private static TripleABC<EnumColour, EnumColour, EnumColour> getTriple(EnumColour a, EnumColour b, EnumColour result) {
        return new TripleABC<EnumColour, EnumColour, EnumColour>(a, b, result);
    }

}