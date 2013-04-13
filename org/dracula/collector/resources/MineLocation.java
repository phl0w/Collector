package org.dracula.collector.resources;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public enum MineLocation {

    VARROCK_EAST(new Tile[]{new Tile(3286, 3370, 0), new Tile(3289, 3375, 0), new Tile(3289, 3380, 0), new Tile(3289, 3387, 0), new Tile(3289, 3392, 0), new Tile(3289, 3397, 0),
            new Tile(3291, 3402, 0), new Tile(3290, 3407, 0), new Tile(3289, 3412, 0), new Tile(3289, 3417, 0), new Tile(3285, 3422, 0), new Tile(3279, 3427, 0), new Tile(3271, 3428, 0),
            new Tile(3266, 3428, 0), new Tile(3260, 3428, 0), new Tile(3255, 3428, 0), new Tile(3254, 3424, 0), new Tile(3254, 3422, 0)},
            new Area(new Tile(3249, 3424, 0), new Tile(3257, 3419, 0)), new int[][]{{11960, 11961, 11962},
            {11957, 11958, 11959},
            {11954, 11955, 11956},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1}}),
    VARROCK_WEST(new Tile[]{new Tile(3183, 3378, 0), new Tile(3179, 3381, 0), new Tile(3180, 3387, 0), new Tile(3180, 3392, 0), new Tile(3175, 3395, 0), new Tile(3170, 3399, 0),
            new Tile(3171, 3407, 0), new Tile(3171, 3413, 0), new Tile(3172, 3419, 0), new Tile(3171, 3424, 0), new Tile(3175, 3429, 0), new Tile(3180, 3430, 0), new Tile(3185, 3430, 0),
            new Tile(3188, 3435, 0)},
            new Area(new Tile(3195, 3431, 0), new Tile(3178, 3447, 0)), new int[][]{{-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {15504},
            {-1},
            {-1},
            {-1}});

    //Mine areas
    public static final Area VARROCKWEST_IMINE = new Area(new Tile(3174, 3381, 0), new Tile(3185, 3381, 0),
            new Tile(3184, 3366, 0), new Tile(3170, 3364, 0));
    public static Area VARROCKEAST_IMINE = new Area(new Tile(3277, 3373, 0), new Tile(3273, 3357, 0),
            new Tile(3297, 3353, 0), new Tile(3296, 3372, 0));

    private Tile[] path;
    private Area bank;
    int[][] oreIds;

    public static final int COPPER = 0, TIN = 1, IRON = 2, SILVER = 3, COAL = 4, GOLD = 5, MITHRIL = 6, ADAMANITE = 7, RUNITE = 8, CLAY = 9;

    private MineLocation(final Tile[] path, final Area bank, final int[][] oreIds) {
        this.path = path;
        this.bank = bank;
        this.oreIds = oreIds;
    }

    public Tile[] getPath() {
        return path;
    }

    public Area getBank() {
        return bank;
    }

    public int[] getIds(int ore) {
        return oreIds[ore];
    }
}