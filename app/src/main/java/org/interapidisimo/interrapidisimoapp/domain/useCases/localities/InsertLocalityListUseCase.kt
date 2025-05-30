package org.interapidisimo.interrapidisimoapp.domain.useCases.localities

import org.interapidisimo.interrapidisimoapp.data.database.entities.LocalityEntity
import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class InsertLocalityListUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    suspend operator fun invoke(localityModelList: List<LocalityModel>) {
        localityModelList.forEach{ localityModel ->
            mainRepository.insertLocality(
                localityEntity = LocalityEntity(
                    localityId = localityModel.localityId,
                    localityTypeId = localityModel.localityTypeId,
                    ancestorPGradeId = localityModel.ancestorPGradeId,
                    ancestorSGradeId = localityModel.ancestorSGradeId,
                    ancestorSGradeName = localityModel.ancestorSGradeName,
                    ancestorTGradeId = localityModel.ancestorTGradeId,
                    ancestorTGradeName = localityModel.ancestorTGradeName,
                    name = localityModel.name,
                    shortName = localityModel.shortName,
                    ancestorPGradeName = localityModel.ancestorPGradeName,
                    fullName = localityModel.fullName,
                    typeLocalityName = localityModel.typeLocalityName,
                    inAreaAssigned = localityModel.inAreaAssigned,
                    inAreaOriginAssigned = localityModel.inAreaOriginAssigned,
                    availableLocality = localityModel.availableLocality,
                    areaName = localityModel.areaName,
                    zipCode = localityModel.zipCode,
                    indicative = localityModel.indicative,
                    serviceCenterId = localityModel.serviceCenterId,
                    registerState = localityModel.registerState,
                    localitiesTypes = localityModel.localitiesTypes,
                    allowsCollection = localityModel.allowsCollection,
                    maxHourCollection = localityModel.maxHourCollection,
                    georeferenceSe = localityModel.georeferenceSe,
                    valueCollection = localityModel.valueCollection,
                    allowsPreshipmentPoint = localityModel.allowsPreshipmentPoint,
                    deliveryLabel = localityModel.deliveryLabel,
                    minHourCollection = localityModel.minHourCollection,
                    abbreviationCity = localityModel.abbreviationCity
                )
            )
        }
    }
}