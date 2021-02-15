package logic;

public class Armor {
    private  ArmorPart head;
    private  ArmorPart leftArm;
    private  ArmorPart rightArm;
    private  ArmorPart leftLeg;
    private  ArmorPart rightLeg;
    private  ArmorPart corp;

    public Armor(){
        ArmorPart head=null;
        ArmorPart leftArm=null;
        ArmorPart rightArm=null;
        ArmorPart leftLeg=null;
        ArmorPart rightLeg=null;
        ArmorPart corp=null;
    }

    public ArmorPart setArmor(ArmorPart part){
        ArmorPart ret=null;
        if(part.getBodyPart().equals(armorPartEnum.HEAD)){
            ret=head;
            head=part;
        } else if(part.getBodyPart().equals(armorPartEnum.LEFTARM)){
            ret=leftArm;
            leftArm=part;
        } else if(part.getBodyPart().equals(armorPartEnum.RIGHTARM)){
            ret=rightArm;
            rightArm=part;
        } else if(part.getBodyPart().equals(armorPartEnum.CORP)){
            ret= corp;
            corp =part;
        } else if(part.getBodyPart().equals(armorPartEnum.RIGHTLEG)){
            ret=rightLeg;
            rightLeg=part;
        } else if(part.getBodyPart().equals(armorPartEnum.LEFTLEG)){
            ret=leftLeg;
            leftLeg=part;
        }
        return ret;
    }

    public ArmorPart actualArmor(ArmorPart part){
        if(part.getBodyPart().equals(armorPartEnum.HEAD)){
            return head;
        } else if(part.getBodyPart().equals(armorPartEnum.LEFTARM)){
            return leftArm;
        } else if(part.getBodyPart().equals(armorPartEnum.RIGHTARM)){
            return rightArm;
        } else if(part.getBodyPart().equals(armorPartEnum.CORP)){
            return corp;
        } else if(part.getBodyPart().equals(armorPartEnum.RIGHTLEG)){
            return rightLeg;
        } else if(part.getBodyPart().equals(armorPartEnum.LEFTLEG)){
            return leftLeg;
        }
        return null;
    }

    public ArmorPart getHead() {
        return head;
    }

    public void setHead(ArmorPart head) {
        this.head = head;
    }

    public ArmorPart getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(ArmorPart leftArm) {
        this.leftArm = leftArm;
    }

    public ArmorPart getRightArm() {
        return rightArm;
    }

    public void setRightArm(ArmorPart rightArm) {
        this.rightArm = rightArm;
    }

    public ArmorPart getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(ArmorPart leftLeg) {
        this.leftLeg = leftLeg;
    }

    public ArmorPart getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(ArmorPart rightLeg) {
        this.rightLeg = rightLeg;
    }

    public ArmorPart getCorp() {
        return corp;
    }

    public void setCorp(ArmorPart corp) {
        this.corp = corp;
    }

}
