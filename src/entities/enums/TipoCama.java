package entities.enums;

public enum TipoCama {

    CASAL(1),
    SOLTEIRO(2),
    BELICHE(3),
    TRES_CAMAS(4);

    private int code;
    private TipoCama(int code){
        this.code=code;
    }

    public int getCode(){
        return code;
    }

    public static TipoCama valueOf(int code){
        for (TipoCama value: TipoCama.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code.");
    }


}
