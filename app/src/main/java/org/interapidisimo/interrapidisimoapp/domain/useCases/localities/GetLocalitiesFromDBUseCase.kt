package org.interapidisimo.interrapidisimoapp.domain.useCases.localities

import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetLocalitiesFromDBUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<LocalityModel> {
        var localityList: ArrayList<LocalityModel> = arrayListOf()
        mainRepository.getLocalitiesFromDB().forEach { localityEntity -> 
            localityList.add(
                LocalityModel(
                    localityId = localityEntity.localityId,
                    localityTypeId = localityEntity.localityTypeId,
                    ancestorPGradeId = localityEntity.ancestorPGradeId,
                    ancestorSGradeId = localityEntity.ancestorSGradeId,
                    ancestorSGradeName = localityEntity.ancestorSGradeName,
                    ancestorTGradeId = localityEntity.ancestorTGradeId,
                    ancestorTGradeName = localityEntity.ancestorTGradeName,
                    name = localityEntity.name,
                    shortName = localityEntity.shortName,
                    ancestorPGradeName = localityEntity.ancestorPGradeName,
                    fullName = localityEntity.fullName,
                    typeLocalityName = localityEntity.typeLocalityName,
                    inAreaAssigned = localityEntity.inAreaAssigned,
                    inAreaOriginAssigned = localityEntity.inAreaOriginAssigned,
                    availableLocality = localityEntity.availableLocality,
                    areaName = localityEntity.areaName,
                    zipCode = localityEntity.zipCode,
                    indicative = localityEntity.indicative,
                    serviceCenterId = localityEntity.serviceCenterId,
                    registerState = localityEntity.registerState,
                    localitiesTypes = localityEntity.localitiesTypes,
                    allowsCollection = localityEntity.allowsCollection,
                    maxHourCollection = localityEntity.maxHourCollection,
                    georeferenceSe = localityEntity.georeferenceSe,
                    valueCollection = localityEntity.valueCollection,
                    allowsPreshipmentPoint = localityEntity.allowsPreshipmentPoint,
                    deliveryLabel = localityEntity.deliveryLabel,
                    minHourCollection = localityEntity.minHourCollection,
                    abbreviationCity = localityEntity.abbreviationCity
                )
            )
        }
        return localityList
    }
}