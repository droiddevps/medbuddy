package com.medbuddy.model

import com.google.gson.annotations.SerializedName

data class MainModelResponse(

    @field:SerializedName("problems")
    val problems: List<ProblemsItem>
)

data class AssociatedDrugItem(

    @field:SerializedName("dose")
    val dose: String,

    @field:SerializedName("strength")
    val strength: String,

    @field:SerializedName("name")
    val name: String
)

data class AsthmaItem(
    val any: Any? = null
)

data class ProblemsItem(

    @field:SerializedName("Diabetes")
    val diabetes: List<DiabetesItem>,

    @field:SerializedName("Asthma")
    val asthma: List<AsthmaItem>
)

data class ClassNameItem(

    @field:SerializedName("associatedDrug")
    val associatedDrug: List<AssociatedDrugItem>,

    @field:SerializedName("associatedDrug#2")
    val associatedDrug2: List<AssociatedDrugItem>
)

data class MedicationsItem(

    @field:SerializedName("medicationsClasses")
    val medicationsClasses: List<MedicationsClassesItem>
)

data class AssociatedDrug2Item(

    @field:SerializedName("dose")
    val dose: String,

    @field:SerializedName("strength")
    val strength: String,

    @field:SerializedName("name")
    val name: String
)

data class MedicationsClassesItem(

    @field:SerializedName("className2")
    val className2: List<ClassNameItem>,

    @field:SerializedName("className")
    val className: List<ClassNameItem>
)

data class LabsItem(

    @field:SerializedName("missing_field")
    val missingField: String
)

data class DiabetesItem(

    @field:SerializedName("labs")
    val labs: List<LabsItem>,

    @field:SerializedName("medications")
    val medications: List<MedicationsItem>
)

data class ClassName2Item(

    @field:SerializedName("associatedDrug")
    val associatedDrug: List<AssociatedDrugItem>,

    @field:SerializedName("associatedDrug#2")
    val associatedDrug2: List<AssociatedDrug2Item>
)
