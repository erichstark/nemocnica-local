<#-- @ftlvariable name="appointments" type="sk.stuba.fei.team.local.domain.Appointment[]" -->
<#-- @ftlvariable name="office" type="sk.stuba.fei.team.local.domain.Office" -->
<#import "../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.menuFooterPage pageTitle="Profil ambulancie">

<br>
<a class="btn btn-info btn-sm" href="<@spring.url '/office/${office.id}'/>" role="button">Naspäť</a>

<div class="panel-body">
    <h1>${office.name}</h1>
    <hr>
    <div class="form-group">
        <h3>Zoznam objednávok</h3>
        <br>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 40px;">#</th>
                <th style="width: 125px;">Dátum</th>
                <th style="width: 100px;">Čas</th>
                <th>Pacient</th>
                <th>Telefón</th>
                <th>Email</th>
                <th>Stav</th>
                <th style="width: 100px;">Detail</th>
            </tr>
            </thead>
            <tbody id="employees">
                <#list appointments?sort_by('date') as appointment>
                <tr>
                    <td>${appointment_index+1}</td>
                    <td>${appointment.date?date}</td>
                    <td>${(appointment.intervalStart/60)?string["0"]}:${(appointment.intervalStart%60)?string["00"]}</td>
                    <td>${appointment.patient.prefix_title!} ${appointment.patient.firstName!} ${appointment.patient.surname!}
                        ${appointment.patient.suffix_title!}</td>
                    <td>${appointment.patient.phone!}</td>
                    <td>${appointment.patient.email!}</td>
                    <td><@spring.message "canceled." + appointment.patient.enabled /></td>
                    <td><a href="<@spring.url '/office/${office.id}/patientdetail/${appointment.patient.username}'/>"
                           style="color: #5bc0de">
                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                    </a></td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>
<script src="<@spring.url '/js/office/hours.js'/>"></script>
</@pt.menuFooterPage>