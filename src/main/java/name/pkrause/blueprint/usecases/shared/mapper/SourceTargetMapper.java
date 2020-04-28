package name.pkrause.blueprint.usecases.shared.mapper;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.usecases.cat.create.CreateCatRequest;
import name.pkrause.blueprint.usecases.cat.create.CreateCatResponse;
import name.pkrause.blueprint.usecases.cat.get.GetCatResponse;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsResponse;
import name.pkrause.blueprint.usecases.cat.update.UpdateCatRequest;
import name.pkrause.blueprint.usecases.cat.update.UpdateCatResponse;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerResponse;
import name.pkrause.blueprint.usecases.catowner.getowners.CatOwnerDto;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersResponse;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SourceTargetMapper {

    SourceTargetMapper MAPPER = Mappers.getMapper(SourceTargetMapper.class);

    Cat toCat(CreateCatRequest request);

    CatOwner toCatOwner(CreateCatOwnerRequest request);

    CreateCatResponse toCreateCatResponse(Cat cat);

    CreateCatOwnerResponse toCreateCatOwnerResponse(CatOwner catOwner);

    GetCatResponse toGetCatResponse(Cat cat);

    name.pkrause.blueprint.usecases.cat.getcats.CatDto toCatDto(Cat cat);

    name.pkrause.blueprint.usecases.catowner.get.CatDto toCatDtoOwner(Cat cat);

    GetCatsResponse toGetCatsResponse(PageResult<Cat> pageResult);

    GetCatOwnersResponse toGetCatOwnersResponse(PageResult<CatOwner> pageResult);

    Cat toCat(UpdateCatRequest request);

    CatOwner toCatOwner(UpdateCatOwnerRequest request);

    CatOwnerDto toCatOwnerDto(CatOwner catOwner);

    UpdateCatResponse toUpdateCatResponse(Cat cat);

    UpdateCatOwnerResponse toUpdateCatOwnerResponse(CatOwner catOwner);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "value", source = "value"),
            @Mapping(target = "catOwnersId", source = "catOwner.id")
    })
    UpdateCatRequest toUpdateCatRequest(Cat cat);

    UpdateCatOwnerRequest toUpdateCatOwnerRequest(CatOwner catOwner);
}
