package org.simple;

/**
 * 基础类，方便子类的hash, equals, toString实现。
 * 
 * @author Johnny
 * 
 */
public class CodeBean implements SampleEntity {
    private static final long serialVersionUID = -8562682188238131913L;
    private String code;
    private String name;

    public CodeBean() {
    }

    public CodeBean(String code) {
        super();
        this.code = code;
    }

    public CodeBean(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hs = 17;
        if (code != null) {
            hs = 37 * hs + code.hashCode();
        }
        return hs;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof CodeBean) {
            if (this == obj){
                result = true;
            }
            String code2 = ((CodeBean) obj).getCode();
            if(code2 != null){
                if (this.code.equals(code2)){
                    result = true;
                }
            }
            if (this.code != null) {
                result =  this.code.equals(code2);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return code;
    }

}
