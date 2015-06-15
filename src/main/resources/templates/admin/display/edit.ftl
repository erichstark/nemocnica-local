<#-- @ftlvariable name="originalID" type="java.lang.String" -->
<#-- @ftlvariable name="display" type="sk.stuba.fei.team.local.domain.DisplayConfiguration" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#if originalID?length!=0>
    <#assign title>Editácia obrazovky</#assign>
<#else>
    <#assign title>Nová obrazovka</#assign>
</#if>
<@pt.dashboardPage pageTitle=title>
<a class="btn btn-info" href="<@spring.url '/admin/display'/>" role="button">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    Späť
</a>
<div class="form-group">
    <h3>ID</h3>
    <input type="text" class="form-control" id="id" value="${display.id}">
</div>
<input id="originalID" type="hidden" value="${originalID}"/>

<div class="form-group" style="padding-bottom: 30px">
    <h3>Ordinácie</h3>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Názov</th>
            <th>Špecializácie</th>
            <th>Personál</th>
            <th style="width: 60px;">Odobrať</th>
        </tr>
        </thead>
        <tbody id="offices">
            <#list display.offices as office>
            <tr id="office-${office.id}">
                <td>${office.name}</td>
                <td>
                    <#list office.specializations as specialization>
                    ${specialization.name}<#if specialization_has_next>, </#if>
                    </#list>
                </td>
                <td>
                    <#list office.employees as employee>
                    ${employee.prefix_title!} ${employee.firstName!} ${employee.lastName!} ${employee.suffix_title!} <#if employee_has_next>
                        , </#if>
                    </#list>
                </td>
                <td class="text-center">
                    <button class="btn btn-sm btn-danger remove-office">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
    <h4 id="no-offices" <#if display.offices?has_content>style="display: none"</#if>
        class="text-muted">
        Obrazovka nemá priradené žiadne ordinácie.</h4>
</div>
<div class="input-group">
    <input type="text" class="form-control" id="searchTerm" placeholder="Vyhladať ordináciu..." name="searchTerm">
                <span class="input-group-btn">
                    <button id="searchButton" class="btn btn-default ladda-button" data-style="zoom-in"
                            onclick="search()"><span
                            class="ladda-label">Hladať</span></button>
                </span>
</div>
<div style="display: none" id="search-container">
    <h3>Nájdené ordinácie</h3>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Názov</th>
            <th>Špecializácie</th>
            <th>Personál</th>
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
<script src="<@spring.url '/js/display.js'/>"></script>
</@pt.dashboardPage>