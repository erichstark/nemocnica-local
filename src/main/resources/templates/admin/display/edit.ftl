<#-- @ftlvariable name="searchResults" type="java.util.Collection<sk.stuba.fei.team.local.domain.Office>" -->
<#-- @ftlvariable name="searchTerm" type="java.lang.String" -->
<#-- @ftlvariable name="originalID" type="java.lang.String" -->
<#-- @ftlvariable name="display" type="sk.stuba.fei.team.local.domain.DisplayConfiguration" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#if orinalID??>
    <#assign title>Editácia obrazovky</#assign>
<#else>
    <#assign title>Nová obrazovka</#assign>
</#if>
<@pt.dashboardPage pageTitle=title>
<a class="btn btn-info" href="<@spring.url '/admin/display'/>" role="button">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    Späť
</a>
<form name="display" action="<@spring.url '/admin/display/submit'/>" method="post">
    <div class="form-group">
        <h3>ID</h3>
        <input type="text" name="id" class="form-control" id="office-id" placeholder="ID"
               value="${display.id}">
    </div>
    <input name="originalID" type="hidden" value="${originalID!""}"/>

    <input name="offices" type="hidden" value="
        <#if display.offices?? && display.offices?has_content><#list display.offices as office>
        ${office.id}<#if office_has_next>, </#if>
        </#list></#if>"/>

    <div class="form-group">
        <h3>Ordinácie</h3>
        <#if display.offices?? && display.offices?has_content>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th style="width: 60px;">ID</th>
                    <th>Názov</th>
                    <th>Ordinujúci lekár</th>
                    <th style="width: 60px;">Odobrať</th>
                </tr>
                </thead>
                <tbody>
                    <#list display.offices as office>
                    <tr>
                        <td>${office.id}</td>
                        <td>${office.name}</td>
                        <td>
                            <#list office.employees as employee>
                            ${employee.prefix_title!} ${employee.firstName!} ${employee.lastName!} ${employee.suffix_title!} <#if employee_has_next>
                                , </#if>
                            </#list>
                        </td>
                        <td class="text-center">
                            <button name="submit" class="btn btn-sm btn-danger" value="remove/${office.id}">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </button>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <h4 class="text-muted">Obrazovka nemá priradené žiadne ordinácie.</h4>
        </#if>
    </div>
    <div class="input-group">
        <input type="text" class="form-control" id="searchTerm" placeholder="Vyhladať ordináciu..."
               name="searchTerm"
               <#if searchTerm??>value="${searchTerm}"</#if>>
                <span class="input-group-btn">
                    <button class="btn btn-default" name="submit" value="search">Hladať</button>
                </span>
    </div>
    <#if searchResults??>
        <h3>Nájdené ordinácie</h3>
        <#if searchResults?has_content>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th style="width: 60px;">ID</th>
                    <th>Názov</th>
                    <th>Ordinujúci lekár</th>
                    <th style="width: 60px;">Pridať</th>
                </tr>
                </thead>
                <tbody>
                    <#list searchResults as office>
                    <tr>
                        <td>${office.id}</td>
                        <td>${office.name}</td>
                        <td>
                            <#list office.employees as employee>
                            ${employee.prefix_title!} ${employee.firstName!} ${employee.lastName!} ${employee.suffix_title!} <#if employee_has_next>
                                , </#if>
                            </#list>
                        </td>
                        <td class="text-center">
                            <button name="submit" class="btn btn-sm btn-success" value="add/${office.id}">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            </button>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <h4 class="text-muted">Žiadne nájdené výsledky.</h4>
        </#if>
    </#if>
    <div style="padding-top: 30px" class="form-group">
        <div>
            <button name="submit" class="btn btn-success" value="save">
                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                Uložiť
            </button>
        </div>
    </div>
</form>
</@pt.dashboardPage>