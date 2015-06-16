<#-- @ftlvariable name="office" type="sk.stuba.fei.team.local.domain.Office" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#if !office.id?has_content>
    <#assign pageTitle>Pridanie novej ambulancie</#assign>
<#else>
    <#assign pageTitle>Editácia ambulancie</#assign>
</#if>
<@pt.dashboardPage pageTitle=pageTitle>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info" href="<@spring.url '/admin/office'/>" role="button">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            Späť
        </a>
    </div>
</div>

<hr>

<div class="panel-body" id="form">

    <input type="hidden" name="office_id" value="${office.id!}">

    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <div class="input-group has-warning">
                <span class="input-group-addon">
                    <input type="checkbox" name="enabled" value="true" <#if  office.enabled>checked</#if> >
                </span>
                <span class="form-control">Aktívna ambulancia</span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="office-name">Názov</label>
        <input type="text" name="name" class="form-control" id="office-name" placeholder="Názov"
               value="${office.name!""}">
    </div>
    <div style="padding-top: 30px" class="form-group">
        <div>
            <button id="saveButton" class="btn btn-success ladda-button" data-style="zoom-in" onclick="saveOffice()">
                <span class="ladda-label">
                    <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                    Uložiť
                </span>
            </button>
        </div>
    </div>

    <hr>

    <div class="form-group">
        <h3>Ordináčné hodiny</h3>
        <a class="btn btn-info" href="<@spring.url '/admin/office/hours/${office.id!}'/>" role="button" name="link"
           onclick="return check(this)">
            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
            Upraviť
        </a>
        <#if office.hours?? && office.hours?has_content>
            <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Deň</th>
                <th>Ráno od - do</th>
                <th>Cez deň od - do</th>
            </tr>
            </thead>
            <tbody>
                <#list office.hours?sort_by("date") as hour>
                <tr>
                    <td><#if hour.date == 1>Pondelok</#if><#if hour.date == 2>Utorok</#if><#if hour.date == 3>
                        Streda</#if><#if hour.date == 4>Štvrtok</#if><#if hour.date == 5>Piatok</#if></td>
                    <td>
                        <span>${hour.reservationMorningFrom!}</span> - <span>${hour.reservationMorningTo!}</span>
                    </td>
                    <td><span>${hour.reservationFrom!}</span> - <span>${hour.reservationTo!}</span></td>
                </tr>
                </#list>
            </tbody>
            </table>
        <#else>
            <h4 id="no-results" class="text-muted">Nenastavené ordinačné hodiny.</h4>
        </#if>
    </div>
    <hr>
    <div class="form-group">
        <h3>Poisťovne</h3>
        <a class="btn btn-info" href="<@spring.url '/admin/office/insurance/${office.id!}'/>" role="button" name="link"
           onclick="return check(this)">
            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
            Upraviť
        </a>
        <#if office.insurances?? && office.insurances?has_content>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th style="width: 100px;">#</th>
                    <th style="text-align: left">Názov</th>
                </tr>
                </thead>
                <tbody>
                    <#list office.insurances as insurance>
                    <tr>
                        <td>${insurance_index+1}</td>
                        <td style="text-align: left">${insurance.name}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <h4 class="text-muted">Ordinácia nemá priradené žiadne poisťovne.</h4>
        </#if>
    </div>

    <hr>

    <div class="form-group">
        <h3>Špecializácie</h3>
        <a id="test" class="btn btn-info" href="<@spring.url '/admin/office/specialization/${office.id!}'/>"
           role="button" name="link" onclick="return check(this)">
            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
            Upraviť
        </a>
        <#if office.specializations?? && office.specializations?has_content>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th style="width: 100px;">#</th>
                    <th style="text-align: left">Názov</th>
                </tr>
                </thead>
                <tbody>
                    <#list office.specializations as specialization>
                    <tr>
                        <td>${specialization_index+1}</td>
                        <td style="text-align: left">${specialization.name}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <h4 class="text-muted">Ordinácia nemá priradené žiadne špecializáciu.</h4>
        </#if>
    </div>
    <hr>

    <div class="form-group">
        <h3>Zamestnanci</h3>
        <a class="btn btn-info" href="<@spring.url '/admin/office/employee/${office.id!}'/>" role="button" name="link"
           onclick="return check(this)">
            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
            Upraviť
        </a>
        <#if office.employees?? && office.employees?has_content>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th style="width: 100px;">#</th>
                    <th style="text-align: left">Meno zamestnanca</th>
                </tr>
                </thead>
                <tbody>
                    <#list office.employees as employee>
                    <tr>
                        <td>${employee_index+1}</td>
                        <td style="text-align: left">${employee.firstName!} ${employee.lastName!}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <h4 class="text-muted">Ordinácia nemá priradených žiadnych zamestnancov.</h4>
        </#if>
    </div>
</div>
<script src="<@spring.url '/js/admin/office.js'/>"></script>
</@pt.dashboardPage>