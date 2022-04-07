package capstone.bond.dto;

public enum Symbol {
    KR1YT(29294),KR2YT(29295),KR3YT(29296),KR4YT(29297),KR5YT(29298),KR10YT(29292),KR20YT(29293),KR30YT(1052525),KR50YT(1052526)
    ,US1MT(23697),US3MT(23698),US6MT(23699),US1YT(23700),US2YT(23701),US3YT(23702),US7YT(23704),US10YT(23705);

    private final int currId;

    private Symbol(int currId){
        this.currId=currId;
    }

    public int getCurrId(){
        return this.currId;
    }
}
