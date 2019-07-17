package pruebafbi.dtos;

public class CommonResponseDto {

    private String code;
    private Object body;
    private String type;

    public CommonResponseDto(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public CommonResponseDto(String code, Object body, String type) {
        this.code = code;
        this.body = body;
        this.type = type;
    }


}
