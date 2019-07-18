package com.ibm.rho.estore.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Product
 */

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-18T05:44:56.004Z")

public class Product   {

  private Long id = null;


  private String productId = null;


  private String productName = null;


  private String partNo = null;


  private String imageLink = null;


  private String productShortDesc = null;

  public Product(){
	  
  }
  
  public Product(Long id, String productId, String productName, String partNo, String imageLink, String productShortDesc){
	
	this.id = id;
	this.productId = productId;
	this.productName = productName;
	this.partNo = partNo;
	this.imageLink = imageLink;
	this.productShortDesc = productShortDesc;
  }
  
  public Product id(Long id) {
    this.id = id;
    return this;
  }
  
  

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product productId(String productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
  **/
  @ApiModelProperty(value = "")


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public Product productName(String productName) {
    this.productName = productName;
    return this;
  }

  /**
   * Get productName
   * @return productName
  **/
  @ApiModelProperty(value = "")


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Product partNo(String partNo) {
    this.partNo = partNo;
    return this;
  }

  /**
   * Get partNo
   * @return partNo
  **/
  @ApiModelProperty(value = "")


  public String getPartNo() {
    return partNo;
  }

  public void setPartNo(String partNo) {
    this.partNo = partNo;
  }

  public Product imageLink(String imageLink) {
    this.imageLink = imageLink;
    return this;
  }

  /**
   * Get imageLink
   * @return imageLink
  **/
  @ApiModelProperty(value = "")


  public String getImageLink() {
    return imageLink;
  }

  public void setImageLink(String imageLink) {
    this.imageLink = imageLink;
  }

  public Product productShortDesc(String productShortDesc) {
    this.productShortDesc = productShortDesc;
    return this;
  }

  /**
   * Get productShortDesc
   * @return productShortDesc
  **/
  @ApiModelProperty(value = "")


  public String getProductShortDesc() {
    return productShortDesc;
  }

  public void setProductShortDesc(String productShortDesc) {
    this.productShortDesc = productShortDesc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.id, product.id) &&
        Objects.equals(this.productId, product.productId) &&
        Objects.equals(this.productName, product.productName) &&
        Objects.equals(this.partNo, product.partNo) &&
        Objects.equals(this.imageLink, product.imageLink) &&
        Objects.equals(this.productShortDesc, product.productShortDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productId, productName, partNo, imageLink, productShortDesc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    partNo: ").append(toIndentedString(partNo)).append("\n");
    sb.append("    imageLink: ").append(toIndentedString(imageLink)).append("\n");
    sb.append("    productShortDesc: ").append(toIndentedString(productShortDesc)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


