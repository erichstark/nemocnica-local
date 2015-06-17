<#-- @ftlvariable name="searchTerm" type="java.lang.String" -->
<#-- @ftlvariable name="specializations" type="sk.stuba.fei.team.local.domain.Specialization[]" -->
<#import "../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.menuFooterPage pageTitle="Môj profil">

<div class="panel-body" id="form">

    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-prefix_title">Titul pred</label>
            <input type="text" name="prefix_title" class="form-control" id="employee-prefix_title"
                   placeholder="Titul pred"
                   value="${user.prefix_title!""}">
        </div>
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-suffix_title">Titul za</label>
            <input type="text" name="suffix_title" class="form-control" id="employee-suffix_title"
                   placeholder="Titul za"
                   value="${user.suffix_title!""}">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-firstName">Meno</label>
            <input type="text" name="firstName" class="form-control" id="employee-firstName" placeholder="Meno"
                   value="${user.firstName!""}">
        </div>
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-surname">Priezvisko</label>
            <input type="text" name="lastName" class="form-control" id="employee-surname" placeholder="Priezvisko"
                   value="${user.lastName!""}">
        </div>
    </div>
    <hr>
    <div class="form-group">
        <h3>Špecializácie</h3>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th>Názov</th>
                <th style="width: 60px;">Odobrať</th>
            </tr>
            </thead>
            <tbody id="specializations">
                <#if specializations?? && specializations?has_content>
                    <#list specializations as specialization>
                    <tr id="specialization-${specialization.id}">
                        <td>${specialization_index+1}</td>
                        <td>${specialization.name}</td>
                        <td class="text-center">
                            <button name="submit" class="btn btn-sm btn-danger"
                                    onclick="removeSpecialization(${specialization.id})">
                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                            </button>
                        </td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
        <h4 id="no-specializations"
            <#if specializations?? && specializations?has_content>style="display: none"</#if>class="text-muted">
            Zamestnanec nemá priradené žiadne špecializácie.</h4>
    </div>
    <div class="input-group">
        <input type="text" class="form-control" id="searchTerm" placeholder="Vyhladať špecializáciu..."
               name="searchTerm">
        <span class="input-group-btn">
            <button id="searchButton" class="btn btn-default ladda-button" data-style="zoom-in" onclick="search()">
                <spanc lass="ladda-label">Hladať</span></button>
        </span>
    </div>
    <div style="display: none" id="search-container">
        <h3>Nájdené </h3>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th>Názov</th>
                <th style="width: 60px;">Pridať</th>
            </tr>
            </thead>
            <tbody id="search-results">
            </tbody>
        </table>
        <h4 id="no-results" class="text-muted">Žiadne nájdené výsledky.</h4>
    </div>

    <div style="padding-top: 30px" class="form-group">
        <div>
            <button id="saveButton" class="btn btn-success ladda-button" data-style="zoom-in" onclick="save()">
        <span class="ladda-label">
            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
            Uložiť
        </span>
            </button>
        </div>
    </div>
</div>
<script src="<@spring.url '/js/profil/employee.js'/>"></script>
</@pt.menuFooterPage>