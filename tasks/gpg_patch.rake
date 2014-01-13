require 'buildr/gpg'

raise "Patch applied in latest version of buildr" if Buildr::VERSION >= '1.4.16'

module Buildr
  module GPG
    class << self
      def sign_and_upload_all_packages(project)
        project.packages.each { |pkg| Buildr::GPG.sign_and_upload(project, pkg) }
        project.packages.map { |pkg| pkg.pom }.compact.uniq.each { |pom| Buildr::GPG.sign_and_upload(project, pom) }
      end
    end
  end
end
