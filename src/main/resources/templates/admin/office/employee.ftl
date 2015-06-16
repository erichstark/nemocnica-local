<#-- @ftlvariable name="office" type="sk.stuba.fei.team.local.domain.Office" -->
<#-- @ftlvariable name="searchTerm" type="java.lang.String" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Editácia zamestnancov</#assign>
<@pt.dashboardPage pageTitle=pageTitle>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info" href="<@spring.url '/admin/office/edit/${office.id}'/>" role="button">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            Späť
        </a>
    </div>
</div>

<hr>

<div class="table-responsive" id="form">
    <input name="office_id" type="hidden" value="${office.id!}"/>

    <div class="form-group">
        <h3>Zamestnanci</h3>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th>Prihlasovacie meno</th>
                <th>Meno</th>
                <th style="width: 60px;">Odobrať</th>
            </tr>
            </thead>
            <tbody id="employees">
                <#list office.employees as employee>
                <tr id="employee-${employee.username}">
                    <td>${employee_index+1}</td>
                    <td>${employee.username}</td>
                    <td>${employee.prefix_title!""} ${employee.firstName!""} ${employee.lastName!""} ${employee.suffix_title!""}</td>
                    <td class="text-center">
                        <button name="submit" class="btn btn-sm btn-danger"
                                onclick="removeEmployee('${employee.username}')">
                            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                        </button>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
        <h4 id="no-employee"
            <#if office.employees?has_content>style="display: none"</#if>class="text-muted">
            Ambulancia nemá priradených žiadnych zamestnancov.</h4>
    </div>
    <div class="input-group">
        <input type="text" class="form-control" id="searchTerm" placeholder="Vyhladať poisťovňu..."
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
                <th>Prihlasovacie meno</th>
                <th>Meno</th>
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
<script src="<@spring.url '/js/admin/officeEmployee.js'/>"></script>
</@pt.dashboardPage>